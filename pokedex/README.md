[//]: # (dagger init --sdk=python --source=./dagger)

1. If you are using Intellij install python plugin
2. create a virtual environment: python3 -m venv .venv3.12
3. add .venv3.12 to gitignore
4. activate virtual environment (ubuntu): source .venv3.12/bin/activate
5. add the SDK: file->project structure->SDKs: add the python interpreter:
   point to dagger/venv/bin/python (Linux/macOS) or dagger/venv/Scripts/python.exe (Windows). Call it: .venv3.12
6. add a new module (dagger_python): file->project structure +add module (type python, select) inside the root of the project (in this case, pokedex) , call it dagger_python
   end location as **/**/.venv3.12 and select .venv3.12 as the virtual environment
7. mark the new folder (dagger_python) as python source root (Right-click > Mark Directory as > Sources Root))
8. create a main.py
9. create a requirements.txt
10. dagger_python/.venv3.12 should be marked as excluded (if not Right-click > Mark Directory as > Excluded )
11. finally initialize a dagger module: over the root of the project (in this case, pokedex) execute: dagger init --sdk=python --source=./dagger_python

After this you could run from any tool executing: python3 dagger_python/main.py 