import os
import sys

import anyio
import dagger
import requests
from dagger import dag, BuildArg


async def main():
    # check for GitHub registry credentials in host environment
    for var in ["CR_PAT", "USERNAME"]:
        if var not in os.environ:
            msg = f"{var} environment variable must be set"
            raise OSError(msg)

    # initialize Dagger client
    cfg = dagger.Config(log_output=sys.stderr)
    async with ((dagger.connection(cfg))):
        username = os.environ["USERNAME"]
        # set registry password as secret for Dagger pipeline
        password = dag.set_secret("password", os.environ["CR_PAT"])

        # create a cache volume for Maven downloads
        maven_cache = dag.cache_volume("maven-cache")

        # get reference to source code directory
        project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
        source = dag.host().directory(project_root, exclude=["dagger_python", ".venv3.12"])
        maven_settings = dag.host().file(path="maven-settings.xml")

        # use maven:3.9 container
        # mount cache and source code volumes
        # set working directory
        app = (
            dag.container()
            .from_("maven:3.9-eclipse-temurin-20")
            # .with_mounted_file("/root/.m2/settings.xml",maven_settings)
            .with_mounted_cache("/root/.m2", maven_cache)
            .with_mounted_directory("/app", source)
            .with_workdir("/app")
        )

        # build = (
        #     app.with_exec(["mvn", "clean", "package"])
        # )
        #
        # # use eclipse alpine container as base
        # # copy JAR files from builder
        # # set entrypoint and database profile
        # deploy = (
        #     dag.container()
        #     .from_("eclipse-temurin:20-alpine")
        #     .with_directory("/app", build.directory("./target"))
        #     .with_entrypoint(
        #         [
        #             "java",
        #             "-jar",
        #             "/app/pokedex-0.0.1-SNAPSHOT.jar",
        #         ]
        #     )
        # )

        package = (
            app.with_exec(["mvn", "clean", "package"])
        )

        # build the Docker image using the JAR file and tag it with the Git commit SHA
        # Clone the GitHub repository
        repo_url = "https://github.com/Javier-Godon/ddd-hexagonal-vertical-slice-cqrs-reactive-kubernetes"
        git_repo = dag.git(repo_url)
        branch = git_repo.branch("main")
        # Get the latest commit SHA
        latest_commit = await branch.commit()  # this returns the sha of the commit
        # short_sha =  f"{latest_commit[:7]}"  # Shorten the commit hash for tagging

        print(f"Latest commit SHA: {latest_commit}")
        build_image = (
            dag.container()
            .with_mounted_directory("/app", source)
            .with_workdir("/app")
            .with_directory("/app", package.directory("./target"))
            .directory("/app")
            .docker_build(build_args=[BuildArg("tag", latest_commit)], dockerfile="./Dockerfile")
            # builds from Dockerfile
        )

        # publish image to registry
        image_tag = latest_commit
        image_address = f"ghcr.io/{username.lower()}/ddd-hexagonal-vertical-slice-cqrs-reactive-kubernetes:{image_tag}"
        address = await build_image.with_registry_auth(
            "ghcr.io", username, password
        ).publish(image_address)

        # print image address
        print(f"Image published at: {address}")

        # Trigger GitHub Action via repository_dispatch
        dispatch_url = "https://api.github.com/repos/Javier-Godon/cluster-continuous-delivery/dispatches"
        headers = {
            "Authorization": f"token {os.environ['CR_PAT']}",
            "Accept": "application/vnd.github+json"
        }

        payload = {
            "event_type": "image-tag-in-pokedex-dev-updated",
            "client_payload": {
                "image_tag": image_tag
            }
        }

        response = requests.post(dispatch_url, json=payload, headers=headers)

        if response.status_code == 204:
            print("GitHub Action triggered successfully")
        else:
            print(f"Failed to trigger GitHub Action: {response.status_code} {response.text}")


anyio.run(main)
