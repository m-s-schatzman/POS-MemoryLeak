#!/bin/bash

set -v

mkdir -p Output

cp Libraries/* Output/

rm Output/*.class

javac -g -d Output -classpath ./:Output/:Model/:View/:Controller/:Misc/ Main.java

javac -g -d Output -classpath ./:Output/:Model/:View/:Controller/:Misc/ Misc/DBDriver.java