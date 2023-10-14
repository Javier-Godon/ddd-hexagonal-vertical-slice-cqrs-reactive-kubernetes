### Deployment on Kubernetes

This application has been configured to be deployed in a Kubernetes cluster.

To deploy the infrastructure and the application you will need a Kubernetes cluster with kubectl
If you don't have access to one and need a quick installation there are some ways to get one really quickly
- https://docs.rancherdesktop.io/getting-started/installation/
- https://docs.k3s.io/quick-start
- https://microk8s.io/

For the purpose of this demo I will use microk8s (a little more work is needed to be configured, but not too much)

To know the IP of my cluster execute the next command (or go to nodes in rancherdesktop if you have this one)

``` 
kubectl get nodes -o wide
```
Copy the INTERNAL-IP

Edit your etc/hosts adding the line:

``
<INTERNAL-IP>     bluesolution-pokedex.com
``

go to deployment folder ,open a terminal and execute the command

```
kubectl apply -f namespaces.yaml
```

go to deployment/kustomize-postgres ,open a terminal and execute the command

```
kubectl apply -k ./
```
Then you should have a postgresql deployed in your kubernetes cluster

### Play with it in local

If we run it in local (for instance using Intellij) with the infrastructure deployed (Postgresql instance) 

OpenApi documentation access:
- http://localhost:8089/pokedex/api-docs
- http://localhost:8089/pokedex/swagger/api-docs-ui

Use postman collection bluesolution_pokedex_local


### Play with it in Kubernetes

First you will need to create the Docker image

Open a terminal at the root of the project (pokedex folder) and execute:

```
mvn clean install
```
and then ...

Kubernetes requires a registry to pull the images from it.
As we are doing it locally we need to use the registry provided for our kubernetes installation (microk8s for example)
See:
- https://microk8s.io/docs/registry-images
- https://docs.k3s.io/installation/private-registry
- https://docs.rancherdesktop.io/tutorials/working-with-images/

If you are using microk8s execute this commands:
(If not, check de documentation that applies to you)

```
microk8s.enable registry

docker build -t localhost:32000/pokedex:v0.0.1-SNAPSHOT . 

docker push localhost:32000/pokedex:v0.0.1-SNAPSHOT 

```

Once we have the image in our kubernetes local registry we can deploy the application.

**Important:** If you are using another cluster different from microk8s and you have defined another image name, you will have to change that name in 
``
pokedex-deployment.yaml
``

`` 
deployment/kustomize-pokedex/pokedex-deployment.yaml
``
Once this is done 

go to deployment/kustomize-pokedex ,open a terminal and execute the command

```
kubectl apply -k ./
```


OpenApi documentation access:

- http://bluesolution-pokedex.com:31010/pokedex/api-docs
- http://bluesolution-pokedex.com:31010/pokedex/swagger/api-docs-ui

Use postman collection bluesolution_pokedex_kubernetes

