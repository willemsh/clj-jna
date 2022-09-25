#!/bin/bash

#export LD_LIBRARY_PATH="$(pwd)/target/debug/"
#echo "LD_LIBRARY_PATH=$LD_LIBRARY_PATH"



cp ../cljjnarustclib/target/release/*.so ./native

clj -J"-Djna.library.path=/home/willem/git/tests/clojure/bindings/clj-jna/native" #-X h/sdljnav

