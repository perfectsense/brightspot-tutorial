# Brightspot Tutorial

This repository serves as the quickest way to get started trying out [Brightspot](https://www.brightspot.com) and unlocking the power given to developers on the platform. The example project depends on the Brightspot libraries and will serve as the baseline for many of the tutorials found on the [Brightspot Docs Site](http://docs.brightspot.com).

## Quick Start

Clone or download this repository and follow the steps below to get started. You should be comfortable using a terminal or command line tool before proceeding as it will be needed to compile the code using the [Gradle Build Tool](https://gradle.org).

### One-Time Setup

#### Install Java JDK

You'll need to install Java version 8 or later. We recommend the LTS versions 8, 11, or 17 from the [Azul Zulu](https://www.azul.com/downloads/?package=jdk) or [Adoptium Temurin](https://adoptium.net/releases.html) builds.

Verify the installation was successful by navigating to the root of this repository in a terminal and running the following Gradle command:

**Linux/Mac**
```shell script
./gradlew -version
```

**Windows**
```shell script
gradlew -version
```

The correct **JVM** version should be listed in the output. You will now be able to compile the code. The next step in the installation is to be able to run it!

#### Install Docker

We'll use [Docker](https://www.docker.com) to streamline the server setup needed to run Brightspot. Follow the instructions on the [Docker Getting Started Page](https://www.docker.com/get-started) to download Docker for your OS and then install the package to get the Docker daemon up and running on your machine.

### Run Brightspot

#### Build the project

Compile the project using Gradle by executing the following command from the root of the project folder:

**Linux/Mac**
```shell script
./gradlew
```

**Windows**
```shell script
gradlew
```

#### Start the Containers

To create a runtime environment for Brightspot, boot up Docker containers by executing the following command from the root of the project folder.

```shell script
docker/start
```

You can now access the Brightspot from your browser at [http://localhost/cms]([http://localhost/cms]) 🎉

### Additional Tips

#### Rebuild and Redeploy

Most changes you make to the code will automatically get picked up by the Dari Reloader when you refresh your browser window eliminating the need to rebuild and redeploy every time. If such a time comes that you do need a fresh build, you can build and deploy the code with a single command:

```shell script
docker/deploy
```

#### Stop, Start and Destroy the Containers

To stop the containers and reclaim system resources, while still preserving your data, run the following command:

```shell script
docker/stop
```

When you're ready to boot things back up, run the following command:

```shell script
docker/start
```

From there you can resume your development and deployment workflow as described above.

If you want to delete all the data and remove the containers you can run the follow command:

```shell script
docker/down
```

#### Project Structure

All backend Java code should be placed in the `src/main/java` directory. You will find a package already created named `com.brightspot.tutorial` with a `package-info.java` file to verify you are in the right place.

#### Settings

If you want to experiment with different settings you can edit the config file located at `docker-context.properties`. You'll need to restart the containers for your changes to take effect.

### Docker Tips

Docker is managed using [Docker Compose](https://docs.docker.com/compose/) and can be controlled directly with its [CLI](https://docs.docker.com/compose/reference/overview/). We have some shortcuts that make it easier to use for our specific use case though:

```console
docker/initialize
docker/deploy
docker/start <optional services>
docker/stop <optional services>
docker/restart <optional services>
docker/logs <optional services>
docker/ssh <service>
docker/down
```

`initialize` destroys any existing containers by the same name along with their data and then creates them new.

`deploy` compiles the Gradle project, deploys the WAR file, restarts the tomcat container and tails the logs.

`start`, `stop`, or `restart` starts, stops, or restarts all or named containers.

`logs` displays last 1000 lines of logs and follows them from all or named containers. You can `Control-C` to stop following.

`ssh` drops you into an interactive shell inside the named container.

`down` destroys the containers along with their data.

Valid services are:

- `mysql` - [MySQL](https://www.mysql.com/) database [Percona distribution](https://www.percona.com/software/mysql-database)
- `solr` - [Apache Solr](https://lucene.apache.org/solr/) database
- `tomcat` - [Apache Tomcat](http://tomcat.apache.org/) web server
- `apache` - [Apache](https://httpd.apache.org/) web server + [DIMS](https://github.com/beetlebugorg/mod_dims/wiki) web service
- `greenmail` - [Greenmail](https://greenmail-mail-test.github.io/greenmail/) email server
