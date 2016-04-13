#!/bin/bash

rm -f POSLog.txt
touch POSLog.txt

if [ $# -ge 1 ] && [ "$1" == "reset" ]; then
	set -x
	rm -r Output/POSDatabase
elif [ $# -ge 1 ] && [ "$1" == "test" ]; then
	set -x
	java -cp Output/:Output/derby.jar:Output/junit-4.12.jar:Output/hamcrest-core-1.3.jar -Dderby.system.home=Output/ TestSuite
else
    set -x
    java -cp Output/:Output/derby.jar -Dderby.system.home=Output/ Main
fi
