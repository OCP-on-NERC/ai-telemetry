#!/bin/bash

cd $HOME/.local/src/ai-telemetry
env VARS_PATH=$HOME/.local/src/ai-telemetry/vars.yaml COMPUTATE_SRC=$HOME/.local/src/computate SITE_LANG=enUS $HOME/.local/src/computate/bin/enUS/generate.sh

