#!/bin/bash

if [ $# -ge 1 ] && [ "$1" == "reset" ]; then
	set -x
	rm -r Output/POSDatabase
else
    set -x
    java -cp Output/:Output/derby.jar -Dderby.system.home=Output/ Main
fi
