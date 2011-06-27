BUILDING kaarpool webapp
========================

1. Checkout the this directory

$ svn co <svn-url> .

2. This directory should have the following directories...
backend
ui
ui_static_content
build_scripts
db_scripts


3. Create a database at a server. Import the database from the db_scripts

4. Modify the build_scripts/build.properties as per the environment

5. In the terminal, cd to build_scripts
$ cd build_scripts

6. Run ant to build and deploy

... build_scripts $ ant
