# POS-MemoryLeak
Program for CSE216

COMPILATION INSTRUCTIONS

To Compile: From the Application/ folder run the command "./make_script.sh" to run the automated compile process. This will create the OutputTree directory to store the compiled classes and put all compiled classes into that directory.
Note: to run the make_script.sh bash script, you may need to use the command "chmod 700 make_script.sh" while in the Application folder. This should fix problem.

To Create Database: From the Application/ folder run the command "./run_script.sh create" to create database with schema stored in Misc/ProjectDDL.txt. In order to change database schema, please edit that file to reflect changes to the schema. You will then have to remove Output/POSApplication/ folder and recreate database in order to reflect most recent schema changes. Database only needs to be created a single time, it can then be used repeatedly by application as long as the schema isn't altered (hence the "PersistentStorage"). Currently the database needs to be recreated everytime the schema is changed or the application is built on a new machine. Working on better way to handle this problem...

To Run: from the Application/ folder run the command "./run_script.sh" to run the automated run process. This will set the correct directory to run the compiled Main.java class out of.

To Add Files to Compile: in the Application/makefile file, please include the file and the directory it is in, in the list of java files to be compiled. Please ensure the program compiles before pushing changes to the remote repository.


USING GIT

Getting access to remote repository: Please create folder on your own machine to store local copy of the repository. Once inside of this repository use the command "git clone https://GITHUBUSERNAME@github.com/m-s-schatzman/POS-MemoryLeak.git NEWGITREPOSITORYNAME". This will pull the repository onto your own machine. Once this is done, you should be able to do the standard pull, make changes, add, commit, repeat, pull, push workflow. See the slides for more info about how to do this. 

WORKFLOW:

pull latest changes from github (git pull)
make changes you would like
make sure program compiles (./make_script.sh)
add those files you changed to the staging area (git add <filename>)
commit those changes and give the commit a meaningful name (git commit)
pull the most recent changes (git pull)
     -If there are merge conflicts, you need to edit the files where there are conflicts and edit any discrepencies. This is very important and needs to be more carefully done than most other things. Once these manual changes are made you can then do another commit to commit the merge. If there are no merge conflicts there is no need to recommit, it will be done automatically.
finally the changes need to be pushed to github (git push)
