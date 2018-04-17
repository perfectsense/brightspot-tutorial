# Brightspot Tutorials

## Repository Overview

This repository contains several tutorial projects illustrating various aspects of the Brightspot platform. 

## Directories

* **init** - This directory contains a bare-bones project with just enough to start the Hello World tutorial found at http://docs.brightspot.com/cms/developers-guide/tutorials/article-index.html.

* **hello-world** - This directory contains the results of the Hello World tutorial. Readers can compare their results from the Hello World tutorial with this one. In addition, readers can use this directory to skip the Hello World tutorial and proceed directly with the Hello Styleguide tutorial starting at http://docs.brightspot.com/cms/developers-guide/tutorials/hello-world-index.html.

* **hello-styleguide** - This directory contains the results of the Hello Styleguide tutorial. Readers can compare their results from the Hello Styleguide tutorial with this one. 

## Running a Tutorial

1. Clone this repository or [download the zip](https://github.com/perfectsense/brightspot-tutorial/archive/master.zip).

2. Change to one of the above directories.

3. Run `./run.sh` (OSX/Linux) or `run.cmd` (Windows).

The command compiles the Brightspot project and launches the application at `http://localhost:9480`. The Brightspot login page appears at `http://localhost:9480/cms`.

Alternatively, you can download the Brightspot [Vagrantfile](https://s3.amazonaws.com/perfectsense-ops/boxes/brightspot/Vagrantfile), place it in the root of one the directories listed above and run `vagrant up`. For information on how to run and deploy code with Vagrant, please consult the Brightspot [Documentation](http://docs.brightspot.com/).
