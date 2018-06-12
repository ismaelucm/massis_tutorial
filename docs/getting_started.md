# Getting started

[back to main](index.md)

MASSIS has been created using [Maven](https://maven.apache.org/) and [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html). Therefore, a prerrequisite for using MASSIS is to have installed the [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and the last version of [Maven](https://maven.apache.org/) (Currently working with Apache Maven 3.3.9).

Maven can be installed from here: [https://maven.apache.org/install.html](https://maven.apache.org/install.html)

In its current version, MASSIS also uses the [JMonkeyEngine](http://jmonkeyengine.org/) to show the simulation in 3D. The JMonkeyEngine will be installed in the system by Maven, but it requires  the [OpenGL](https://www.opengl.org/) hardware acceleration to be enabled on the system. The following command (in Linux) checks whether OpenGL is installed on the system:


```bash
> sudo apt-get install mesa-utils

> glxinfo | grep "direct rendering"
direct rendering: Yes

```

It is advisable to have installed the [Ogg](https://xiph.org/ogg/) libraries on the system. Its absence does not prevent execution but generates several warning logs.

## Installation

To install Massis,  clone the following repositories:

```bash
> git clone XXXXXXXXX
> git clone https://github.com/ismaelucm/massis_tutorial.git
```

The first project is the core Massis system and the second proyect has the examples for this tutorial.

Move into massis3-4 directory and  compile the project using Maven:

```bash
> cd massis3-4
> mvn compile
> mvn install
```

Next change to the directory massis3-examples and  compile de example project using the python command install.py and following the instructions to configure the server:

```bash
> cd massis3-examples
> python3 install.py
```

Now Massis is installed in the system. To check that everything is working properly, being in the massis3-examples directory, try to  launch the ''hello world'' simulation:


```bash
> cd massis3-examples
> chmod 755 LaunchServer.sh
> ./LaunchServer.sh
```

The first execution takes some minutes to start because the system has to create the navigation meshes and other assets. Wait until the process is finished.

[back to main](index.md)
