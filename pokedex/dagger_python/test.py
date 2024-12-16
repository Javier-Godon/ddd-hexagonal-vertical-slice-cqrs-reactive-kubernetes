import kcl_lib.api as api

args = api.ExecProgram_Args(k_filename_list=["schema.k"])
api = api.API()
yaml_result=api.exec_program(args).yaml_result
with open("new.yaml", 'w') as f:
    f.write(yaml_result)
#





