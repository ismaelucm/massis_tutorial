# Creating new scenarios in MASSIS

[back to main](index.md)

## Before starting

The main directory when the system read the files that describe the different scenarios is:

 massis3-examples-server/src/main/resources/simulationExamples/

 If the new scenarios are created in this directory, is not needed to specify the file path.  The name of the file is enough. Otherwise, the name of the file must be preceded by the relative path, from the root directory (usually *massis3-4-examples*).

## Creating the LUA file

Create a text file with the extension lua and open with your favorite editor.


```bash
cd massis3-examples-server/src/main/resources/simulationExamples/
> touch myFirstSenario.lua
```

## Choose the different behaviors used and the profilers


Then we will create the skeleton of a scenario with the different parts that make it up as shown below.

```LUA
Scenario = {
    Scene="",
    CameraConfig = {
    },
    AgentsDescriptions = {
    }
}
Commands:
```

Next, we are going to establish the scene environment that we want to use. In this example, we use "Faculty_1floor".

```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
    },
    AgentsDescriptions = {
    }
}
Commands:
```

Next, we configure the camera position. The scene "Faculty_1floor" occupies a rectangular space between the position (0,0) and the position (140,110). To put the camera in the middle of the scene, set the position value to (70.0,100.0,55.0). The camera height is the second parameter. Set rotation = { 90.0,0.0,0.0 } and  lookAt = { 0.0, -1.0, 0.0 }

```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 70.0, 100.0, 55.0 },
        rotation = { 90.0,0.0,0.0 },
        lookAt = { 0.0, -1.0, 0.0 }
    },
    AgentsDescriptions = {
    }
}
Commands:
```

Next, launch the simulation to prove that everything is correct.

```bash
>./LaunchServer.sh -f myFirstSenario.lua
```

The simulation must create the scene "Faculty_1floor" with the camera placed in the middle of the scene without any agents. 

Next, we are going to define a behavioural profile. In the first profile, the agents will move from the student's cafeteria to classroom 1. We will named this profile, ReturnToClass.

There are several behaviours pre-made in MASSIS in the directory *massis3-examples-server/src/main/resources/CrowdBehaviors*.

* FollowingPathBehavior.bh
* FollowingPathWithSpeedBehavior.bh
* RunAwayBehavior.bh
* SecurityBehavior.bh
* ThiefBehavior.bh

In this example we selected FollowingPathBehavior. If you open the behavior, you must see the name of the behavior in the attribute Name. In this case the name is Faculty_1floor.

Set the name variable behavior set behavior = "FollowingPath". Configure the SpeedMin and SpeedMax as you want and set the AnimationSpeedReference = 4.0 (See code example). Finally in RewriteParameter set the variable Path to "Class1".

```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 70.0, 100.0, 55.0 },
        rotation = { 90.0,0.0,0.0 },
        lookAt = { 0.0, -1.0, 0.0 }
    },
    AgentsDescriptions = {
        ReturnToClass={
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class1"
            }
        }
    }
}
Commands:
```

Now is the time to create our first agent. In the command section we write the command:

```LUA
Commands:
MassisLua.createHuman("ReturnToClass", 1, "StudentsCafeteria")
```
This command creates an agent at the "StudentsCafeteria" position (at the centre of this area) with the behaviour "ReturnToClass". When the simulation is launched, we can see the agent instantiated in the students' cafeteria, walking to the classroom 1. The path that connects the cafeteria with the classroom is obtained automatically by the system.


## Creating the MASSIS commands

Now is the time to create our first agent. In the command section we write the command:

```LUA
Commands:
MassisLua.createHuman("ReturnToClass", 1, "StudentsCafeteria")
```
This command creates an agent at the "StudentsCafeteria" position (at the centre of this area) with the behaviour "ReturnToClass". When the simulation is launched, we can see the agent instantiated in the students' cafeteria, walking to the classroom 1. The path that connects the cafeteria with the classroom is obtained automatically by the system.


[back to main](index.md)