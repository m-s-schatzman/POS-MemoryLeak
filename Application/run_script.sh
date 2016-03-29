#!/bin/bash

if [ $# -ge 1 ] && [ $1 == "database" ]; then
	set -x
	java -cp Output:Output/derby.jar -Dderby.system.home=Output/ PersistentStorage
	set +x
elif [ $# -ge 1 ]; then
	echo "parameter" "\""$1"\"" "not recognized"
else
    set -x
    java -cp Output Main 
    set +x
fi
