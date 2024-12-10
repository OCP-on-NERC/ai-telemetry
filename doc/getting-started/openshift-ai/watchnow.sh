#!/bin/bash

cd $HOME/ai-telemetry/doc/getting-started/openshift-ai
eval $(./vars.py)
cd $COMPUTATE_SRC
mvn dependency:build-classpath -Dmdep.outputFile=$COMPUTATE_SRC/config/cp.txt -q
env WATCH=true WATCH_NOW=true VARS_PATH=$VARS_PATH COMPUTATE_SRC=$COMPUTATE_SRC SITE_LANG=$SITE_LANG java -cp "$(cat $COMPUTATE_SRC/config/cp.txt):$COMPUTATE_SRC/target/classes" org.computate.frFR.java.RegarderRepertoire
