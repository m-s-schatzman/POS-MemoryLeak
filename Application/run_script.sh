#!/bin/bash

if [ $# -ge 1 ]; then
	set -x
	java -cp Output/:Output/derby.jar -Dderby.system.home=Output/ DBDriver $1
else
    set -x
    java -cp Output/:Output/derby.jar Main
fi
