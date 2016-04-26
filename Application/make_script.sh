#!/bin/bash

set -v

mkdir -p Output

rm Output/*.class

cp Libraries/* Output/

javac -g -d Output -classpath ./:Output/:Model/:View/:Controller/:Misc/ Main.java

javac -g -d Output -classpath ./:Output/:Output/junit-4.12.jar:Model/:View/:Controller/:Misc/:Test/ Test/TestSuite.java

#Run test suite with every build to avoid regression
./run_script.sh test