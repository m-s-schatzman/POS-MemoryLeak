# POS-MemoryLeak
Program for CSE216

COMPILATION INSTRUCTIONS

To Compile: From the Application/ folder run the command "./make_script.sh" to run the automated compile process. This will create the OutputTree directory to store the compiled classes and put all compiled classes into that directory.
Note: to run the make_script.sh bash script, you may need to use the command "chmod 700 make_script.sh" while in the Application folder. This should fix problem.

To Reset Database: To reset the database (delete it) run the run_script.sh with the argument reset ("./run_script.sh reset"). This will delete the database and when the application is started again, it will be reset with the values defined by populateDB in DBDriver.

To Run Test Suite: from the Application/ folder run the command "./run_script.sh test" to run the automated testing process. If the Test class has been added to the main of TestSuite.java, and the tests are declared correctly, the test should run.

To Run: from the Application/ folder run the command "./run_script.sh" to run the automated run process. This will set the correct directory to run the compiled Main.java class out of.

To Log In: POS is initially populated with two users. The first user has username "Normal" with password "Normal" and role Normal. The second user has the username "Admin", password "Admin", and role Admin and has the ability to add/delete user.

TESTING SCHEME

For alpha release, testing will revolve around a combination of integration tests, system tests, and unit tests. For each use case there is an integration test to make sure that actions performed in each Controller work with the Model classes and database tables. Each Controller will therefore have it's own Testing class in the Test/ directory. There will also be an additional system test to make sure database connection is opened and closed correctly at start and stop of system. Lastly unit tests are used to test the main model classes including Sale, Rental, Return, and User. The TestSuite class contains references to each other test class and uses the JUnit framework to run each test class. Check instructions above for running the tests.

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
