#!/bin/bash

cd {{ SITE_SRC }}
env VARS_PATH={{ SITE_SRC }}/vars.yaml \
  COMPUTATE_SRC={{ COMPUTATE_SRC }} \
  COMPUTATE_VERTX_SRC={{ COMPUTATE_VERTX_SRC }} \
  SITE_LANG=enUS \
  {{ COMPUTATE_SRC }}/bin/enUS/watch.sh
