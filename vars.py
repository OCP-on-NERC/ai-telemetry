#!/usr/bin/env python

import jinja2
import pathlib
import os
import io
import argparse
import yaml
import re
import kubernetes
import base64
from openshift.dynamic import DynamicClient
from distutils.util import strtobool

def split(str, separator):
    return str.split(separator)

def basename(path):
    return os.path.basename(path)

def dirname(path):
    return os.path.dirname(path)

def realpath(path):
    return os.path.realpath(path)

def b64decode(str):
    return base64.b64decode(str).decode("utf-8")

def to_bool(s):
    if s == None:
        return False
    else:
        return bool(strtobool(str(s)))

def lookup(lookup, arg1, errors='strict'):
    if lookup == 'env':
        return os.getenv(arg1)
    elif lookup == 'file':
        if os.path.exists(arg1):
            try:
                with open(arg1, 'r') as file:
                    file_content = file.read()
                    return file_content
            except Exception as ex:
                return None
        else:
            return None

def query(type, kind, resource_name, namespace, api_version=None):
    if type == 'kubernetes.core.k8s':
        if kind == 'Secret':
            try:
                v1 = kubernetes.client.CoreV1Api()
                secret = v1.read_namespaced_secret(resource_name, namespace)
                return [secret]
            except Exception as ex:
                #raise Exception('%s: %s - %s' % (k, v, ex), ex)
                return [{"data":{}}]
        elif kind == 'Ingress':
            try:
                v1 = kubernetes.client.NetworkingV1Api()
                ingress = v1.read_namespaced_ingress(resource_name, namespace)
                return [ingress]
            except Exception as ex:
                #raise Exception('%s: %s - %s' % (k, v, ex), ex)
                return [{"spec":{}}]
        elif api_version != None:
            try:
                route = openshift_client.resources.get(api_version='route.openshift.io/v1', kind='Route').get(name=resource_name, namespace=namespace)
                return [route]
            except Exception as ex:
                #raise Exception('%s: %s - %s' % (k, v, ex), ex)
                return [{"spec":{}}]
    return [{"spec":{}}]


# define a custom representer for strings
def quoted_presenter(dumper, data):
    return dumper.represent_scalar('tag:yaml.org,2002:str', data, style="'")

if __name__ == '__main__':
    if os.path.exists('/var/run/secrets/kubernetes.io/serviceaccount/namespace'):
        kubernetes.config.load_incluster_config()
    else:
        kubernetes.config.load_kube_config()
    k8s_client = kubernetes.client.ApiClient()
    openshift_client = DynamicClient(k8s_client)

    parser = argparse.ArgumentParser(description='Parse vars.yaml file for a computate project')
    parser.add_argument('path', nargs='?', default='vars.yaml', help='the path to the vars.yaml file for the project to generate')
    args = parser.parse_args()

    filters = {
        "basename": basename
        , "dirname": dirname
    }

    config = {}
    vars_path = pathlib.Path(args.path).resolve()
    vars_str = vars_path.read_text()
    original_data = yaml.safe_load(vars_str)
    new_data = {}
    new_data["VARS_PATH"] = str(vars_path)
    config["VARS_PATH"] = str(vars_path)
    for k, v in original_data.items():
        if isinstance(v, str) \
                or isinstance(v, int) \
                or isinstance(v, float) \
                or isinstance(v, complex):
            try:
                t = jinja2.Template(v)
                t.environment.filters['split'] = split
                t.environment.filters['basename'] = basename
                t.environment.filters['dirname'] = dirname
                t.environment.filters['realpath'] = realpath
                t.environment.filters['b64decode'] = b64decode
                t.environment.filters['bool'] = to_bool
                t.environment.globals.update(lookup = lookup)
                t.environment.globals.update(query = query)
                new_data[k] = t.render(**new_data)
                config[k] = str(new_data[k])
            except TypeError as ex:
                if str(ex) == "Can't compile non template nodes":
                    new_data[k] = v
                    config[k] = str(new_data[k])
#                else:
#                    raise Exception('%s: %s - %s' % (k, v, ex), ex)
    
    str_io = io.StringIO()
    yaml.add_representer(str, quoted_presenter)
    yaml.dump(config, str_io, width=float('inf'), default_flow_style=False)
    str = str_io.getvalue()
    str = str.replace("': '", "'='")
    str = re.sub(r"'([^']+)'=", r"\1=", str)
    str = re.sub(r"([^=])''", r'\1' + "'\"'\"'", str)
    print(str)
