# AI Telemetry on OpenShift Local docs

## About the open source GPL3 license and copyright for this product

Copyright (c) 2024 Computate Limited Liability Company in Utah, USA

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


## Bash Kernel for Jupyter

For the rest of the course we will be running terminal commands directly from a Jupyter Notebook in VSCode. 
For this you will want to install the Bash Kernel for Jupyter. 
This is easy to install with python pip dependencies. 
Open a terminal on your computer and run the commands below to install the Bash Kernel for Jupyter. 


```bash
%%bash
pip3 install bash_kernel
python3 -m bash_kernel.install
echo DONE
```

If you have previously installed VSCode, you will want to close VSCode and reopen it to load the Bash Kernel. 

## VSCode

Developing software is not easy without the right tools. 
Although I've worked for years with other IDEs like Eclipse, 
the new standard for software development of all kinds is VSCode. 

Click here to download and install VSCode. 

## VSCode Plugins

Once you have VSCode installed and running, take a look at the Plugins on the left. 
VSCode makes it very easy to install very useful plugins. 
Here is a list of plugins we will be using to develop your new data-driven website and API. 
Install the following plugins which will be used for the rest of the course. 

- YAML: YAML Language Support by Red Hat, with built-in Kubernetes syntax support. 
- Jupyter: Jupyter notebook support, interactive programming and computing that supports Intellisense, debugging and more.
- Extension Pack for Java: Popular extensions for Java development that provides Java IntelliSense, debugging, testing, Maven/Gradle support, project management and more

And my favorite plugin if you love Vim: 
- Vim: Vim emulation for Visual Studio Code


