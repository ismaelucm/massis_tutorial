#!/bin/bash
echo "Running com.massisframework.massis3.examples.simulation.LaunchServer "
MAVEN_OPTS="-Xmx3000m -XX:MaxPermSize=512m"
export MAVEN_OPTS
echo "$*"
mvn exec:java -Dexec.mainClass="com.massisframework.massis3.examples.simulation.LaunchServer" -P dev -Dexec.args="$*"
