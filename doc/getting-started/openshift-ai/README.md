# Getting started with ai-telemetry in OpenShift AI

## Access our project pods in the NERC OpenShift Console

- Log in to the [NERC OpenShift Console here](https://console.apps.shift.nerc.mghpcc.org/k8s/ns/ai-telemetry-cbca60/core~v1~Pod). 

## Obtain a CLI token to access project resources in the terminal

- Click on your username in the top right, and select [ Copy login command ]. 
- Paste the command into your terminal. 
- Make sure you are on the `ai-telemetry-cbca60` project

```bash
oc project ai-telemetry-cbca60
```

## Set up an OpenShift AI Workbench

- Visit the [OpenShift AI dashboard in NERC](https://rhods-dashboard-redhat-ods-applications.apps.shift.nerc.mghpcc.org/projects/ai-telemetry-cbca60)
- Click the [ Create Workbench ] button. 
- Give your workbench a unique name for you like your GitHub username. 
- Image selection: VSCode IJava java-17-openjdk
- Container size: Small
- Select "Create new persistent storage"
  - Name: Use the name of your workbench
  - Persistent storage size: 20Gi or greater
- Click [ Create workbench ]

## Set up a rolebinding for the default service account to access other pods in namespace: 

Run these commands in your computer's terminal with a current token. 
First set an environment variable for your workbench name: 

```bash
OPENSHIFT_AI_WORKBENCH=computate
```

Run these commands to create roles and rolebindings for edit access to the project resources in OpenShift from your OpenShift AI Workbench. 

```bash
oc create rolebinding $OPENSHIFT_AI_WORKBENCH-edit --clusterrole=edit \
  --serviceaccount=$(oc project -q):$OPENSHIFT_AI_WORKBENCH

oc create role $OPENSHIFT_AI_WORKBENCH-edit-rolebindings \
  --verb=get,list,watch,create,update,patch,delete \
  --resource=roles,rolebindings

oc create rolebinding $OPENSHIFT_AI_WORKBENCH-edit-rolebindings --role=$OPENSHIFT_AI_WORKBENCH-edit-rolebindings \
  --serviceaccount=$(oc project -q):$OPENSHIFT_AI_WORKBENCH
```

## Fork ai-telemetry repo

You will want to create your own fork of the [ai-telemetry repo](https://github.com/OCP-on-NERC/ai-telemetry) here to do development. 

## Setting up a GitHub personal access token for ai-telemetry development in RHODS

- Create a [new GitHub personal acces token here](https://github.com/settings/personal-access-tokens/new) to your repo. 
- Select "Only select repositories", and select your ai-telemetry fork repo. 
- Under "Content", select "Access: Read and Write". 
- Copy the token to the clipboard, then set up the following environment variables in a Terminal in RHODS: 

```bash
GITHUB_USERNAME=...
GITHUB_TOKEN=...
git config --global user.email '...'
git config --global user.name '...'
echo "https://${GITHUB_USERNAME}:${GITHUB_TOKEN}@github.com" > ~/.git-credentials
git config --global credential.helper store
```

## Disable Java autobuild

The Java autobuild features of VSCode don't seem to work well with multiple Java projects in the same workspace, 
so in this online VSCode environment, it seems we need to disable the Java autobuild feature. 

- Click on the gear icon in the bottom left. 
- Click `Settings`. 
- Search for `autobuild`. 
- Uncheck to disable the auto build. 

## Next...
If you have successfully ran all of the commands above, congratulations, you are ready to move on to the next notebook in the course. 
- If you have additional questions or issues, please [create an issue for the course here](https://github.com/OCP-on-NERC/ai-telemetry/issues). 
- Otherwise, please continue to the next document [00-build-your-vars.ipynb](00-build-your-vars.ipynb). 
