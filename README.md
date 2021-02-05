# Brightspot Tutorial

This repository serves as the quickest way to get started trying out [Brightspot](https://www.brightspot.com) and unlocking the power given to developers on the platform. The example project depends on the Brightspot libraries and will serve as the baseline for many of the tutorials found on the [documentation site](http://docs.brightspot.com).

## Quick Start

Clone or download this repository and follow the steps below to get started. You should be comfortable using a terminal or command line tool before proceeding as it will be needed to compile the code using the [Gradle Build Tool](https://gradle.org).

### One-Time Setup

#### Install Java 8

The quickest way to get Java 8 is to download it from the [AdoptOpenJDK releases page](https://adoptopenjdk.net/releases.html?variant=openjdk8&jvmVariant=hotspot). Find your Operating System and Architecture and be sure to download the **JDK**.

If you chose an installer package, make a note of the installation directory during setup, otherwise unpack the archive file to a location of your choice.

Set the `JAVA_HOME` environment variable to point to the sub-directory `Contents/Home` within your JDK installation directory from the previous step.

For example, on a Mac, you might execute a command like this:

```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
```

Verify the installation was successful by navigating to the root of this repository in a terminal and running the following Gradle command:

**Linux/Mac**
```shell script
./gradlew -version
```

**Windows**
```shell script
gradlew -version
```

The **JVM** version listed should be of the form `1.8.x`. You will now be able to compile the code. The next step in the installation is to be able to run it!

#### Install Docker

We'll use [Docker](https://www.docker.com) to streamline the server setup needed to run Brightspot. Follow the instructions on the [Docker Getting Started Page](https://www.docker.com/get-started) to download Docker for your OS and then install the package to get the Docker daemon up and running on your machine.

### Run Brightspot

#### Start the Containers

To create a runtime environment for Brightspot, boot up Docker containers with the following command:

```shell script
docker/initialize
```

#### Build and Deploy the Project

You can build and deploy your code with a single command:

**Linux/Mac**
```shell script
./gradlew docker
```

**Windows**
```shell script
gradlew docker
```

You can now access the CMS in your browser at `http://localhost/cms`

Most changes you make to the code will automatically get picked up by the Dari Reloader when you refresh your browser window eliminating the need to rebuild and redeploy every time. If such a time comes that you do need a fresh build just re-run the command above.

#### Stop the Containers

To stop the containers, while still preserving their data, run the following command:

```shell script
docker/stop
```

#### Project Structure

All backend Java code should be placed in the `src/main/java` directory. You will find a package already created named `com.brightspot` with a `package-info.java` file to verify you are in the right place.

#### Settings

If you want to experiment with different settings you can edit the config file located at `docker/brightspot-context.properties`. You'll need to restart the containers for your changes to take effect.

### Docker Tips

Docker is managed using [Docker Compose](https://docs.docker.com/compose/) and can be controlled directly with its [CLI](https://docs.docker.com/compose/reference/overview/). We have some shortcuts that make it easier to use for our specific use case though:

```console
docker/initialize
docker/start <optional services>
docker/stop <optional services>
docker/restart <optional services>
docker/logs <optional services>
```

`initialize` destroys any existing containers along with their data and then creates them new.

`start`, `stop`, or `restart` starts, stops, or restarts all or named containers.

`logs` displays last 1000 lines of logs and follows them from all or named containers. You can `Control-C` to stop following.

Valid services are:

- `mysql` - [MySQL](https://www.mysql.com/) database [Percona distribution](https://www.percona.com/software/mysql-database)
- `solr` - [Apache Solr](https://lucene.apache.org/solr/) database
- `tomcat` - [Apache Tomcat](http://tomcat.apache.org/) web server
- `apache` - [Apache](https://httpd.apache.org/) web server + [DIMS](https://github.com/beetlebugorg/mod_dims/wiki) web service
- `greenmail` - [Greenmail](https://greenmail-mail-test.github.io/greenmail/) email server
