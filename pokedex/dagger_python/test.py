import kcl_lib.api as api

args = api.ExecProgram_Args(k_filename_list=["schema.k"])
api = api.API()
yaml_result=api.exec_program(args).yaml_result
with open("new.yaml", 'w') as f:
    f.write(yaml_result)

# input_file = 'apps/deployments/pokedex/dev'
# output_file = 'apps/deployments/pokedex/dev/manifests/kubernetes-manifests.yaml'
#
# args = api.ExecProgram_Args(k_filename_list=[input_file])
# api = api.API()
# yaml_result = api.exec_program(args).yaml_result
#
# with open(output_file, 'w') as f:
#     f.write(yaml_result)
#
# print(f'YAML manifests generated and saved to {output_file}')




