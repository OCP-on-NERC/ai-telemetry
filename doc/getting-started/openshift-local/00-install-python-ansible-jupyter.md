# AI Telemetry on OpenShift Local docs

## About the open source GPL3 license and copyright for this product

Copyright © 2024 Computate Limited Liability Company in Utah, USA

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

ADDITIONAL TERMS

As stated in section 7. c) and e) of the GPL3 license, 
"you may supplement the terms of this License with terms," 
Computate has added the following additional terms to the license: 

  7 c) Prohibiting misrepresentation of the origin of that material, and
    requiring that modified versions of such material be marked in
    reasonable ways as different from the original version;

  7 e) Declining to grant rights under trademark law for use of some
    trade names, trademarks, or service marks;

Please do not redistribute this course until you have built your own platform with these tools, 
separate from the computate.org platform, and reconfigure your fork of this repo to deploy 
your own platform instead of the computate.org platform. 

QUESTIONS

For questions about this open source license, please contact our public mailing list at computate@group.computate.org


# Installing workbench prerequisites

> "I just finished my 8 story point feature for this sprint, can you please review my pull request" **- Software Engineer**

> "Did you know there's a pip package for that feature?" **- Software Architect**

Have you ever written an exciting piece of code, or automation, just to realize from somebody else that there's already an open source package available for that? That's how I felt when I first learned about Ansible automation, and how I had been wasting my time automating simple things that were already freely available to me. I want to set you up with the knowledge, and best tools available to easily deploy complex edge-to-cloud solutions like the Smart Village Platform. You can build your own solutions using the most up-to-date enterprise open source tools available, and I will show you how. 

For the rest of the course material to run smooth, you will need to run this list of prerequisite commands the first time you run the course, and again if your course is shut down after being idle. The python environment and it's extra packages that are required are reset, when this workbench is shutdown and restarted. **In case you are restarting the workbench**, you should only need to run the second section `Install prerequisite Ansible automation tools` below. 

## Set VSCode Kernel to /usr/bin/python3 Python Environment

## Install Python VirtualEnv


```python
pip3 install virtualenv
echo DONE
```

Install a new python VirtualEnv


```python
virtualenv ~/.local/opt/python
ls ~/.local/opt/python/bin/python
echo DONE
```

Update your ~/.bashrc to use the new Python VirtualEnv


```python
which python
echo "source ~/.local/opt/python/bin/activate" | tee -a ~/.bashrc
source ~/.bashrc
which python
```

# Install prerequisite Ansible automation tools

## Install prerequisite Python packages for Ansible

Whenever I install Ansible, I find there are some required Python dependencies. Install the `setuptools_rust` and `wheel` Python dependencies below. 


```python
pip3 install setuptools_rust wheel
echo DONE
```

## Upgrade pip the python package manager

Next upgrade pip, the python package manager for the latest python package support. 


```python
pip3 install --upgrade pip
echo DONE
```

## Install Ansible automation tools
Ansible is the enterprise open source standard tool for automating everything on the computer. In my opinion, if you are automating your computers and cloud environments without Ansible, you are doing it wrong. Install the latest ansible software with python pip, as well as other important python dependencies like `kubernetes`, `openshift`, and `jmespath` which are required to automate OpenShift deployments. Then check that the `ansible-playbook` command is available to run. 


```python
pip3 install ansible kubernetes openshift jinja2 jmespath pika --upgrade
which ansible-playbook
echo DONE
```
