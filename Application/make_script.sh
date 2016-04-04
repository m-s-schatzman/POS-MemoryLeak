#!/bin/bash

set -v

mkdir -p Output

rm Output/*.class

cp Libraries/* Output/

javac -g -d Output -classpath ./:Output/:Model/:View/:Controller/:Misc/ Main.java

javac -g -d Output -classpath ./:Output/:Model/:View/:Controller/:Misc/ Misc/DBDriver.java