# Getting started

[back to main](index.md)

This tool has been created using maven and Java 8. To install the tool is necessary to have installed the [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 8 SDK and the last version of [Maven](https://maven.apache.org/) (Currently working with Apache Maven 3.3.9).

Maven can be installed from here: [https://maven.apache.org/install.html](https://maven.apache.org/install.html)

The tool uses the [JMonkeyEngine](http://jmonkeyengine.org/) to draw the simulation in 3D. This 3D framework will be installed in the system by maven, but this framework requires that the OpenGL hardware acceleration is enabled on the system. The following command checking whether OpenGL is installed on the system:


```bash
> sudo apt-get install mesa-utils

> glxinfo | grep "direct rendering"
direct rendering: Yes

```

It is advisable to have installed the ogg libraries on the system. Its absence does not prevent execution but generates a warning.

## Installation

To instal the tool, you must clone the following repository:

```bash
> git clone XXXXXXXXX
> git clone YYYYYYYYY
```

The first project is the core System and the second proyect is the examples of the proyect.

Move into massis3-4 directory and type the following commands to compile de proyect using maven.

```bash
> cd massis3-4
> mvn compile
> mvn install
```

Next change the directory to massis3-examples and type the following commands to compile de example proyect using maven.

```bash
> cd massis3-examples
> mvn compile
> mvn install
```

Now the tool is installed in the system. To check whether everything is working properly you can change directory to massis3-examples if it is not there yet and launch the hello world simulation typing:


```bash
> cd massis3-examples
> chmod 755 LaunchServer.sh
> ./LaunchServer.sh
```

[back to main](index.md)