# Creating new scenarios in MASSIS

[back to main](index.md)

## Before starting

The main directory where the system reads the files that describe the different scenarios for this tutorial is:

```bash
massis3-examples-server/src/main/resources/simulationExamples/
```

 If  new scenarios are created in this directory, there is no needed to specify the file path.  The name of the file is enough. Otherwise, the name of the file must be preceded by the relative path, from the root directory (in this case, *massis3-4-examples*).

## Creating the LUA file

Create a text file with the extension *.lua* and open it with your favorite editor (vi, gedit, sublime, etc.)

```bash
cd massis3-examples-server/src/main/resources/simulationExamples/
> vi myFirstSenario.lua
```

## Choose the different behaviors used and the profilers

Then  create the skeleton of a scenario with the different parts that make it up as shown below.

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

The first step is to  establish the scene environment  to use. In this example, we are using the first floor of the building: *"Faculty_1floor"*.

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

The next step is to configure the camera position. The scene *Faculty_1floor* occupies a rectangular space between the position (0,0) and the position (140,110). To put the camera in the middle of the scene, set the position value to (70.0,100.0,55.0). The camera height is the second parameter. Set rotation = { 90.0,0.0,0.0 } and  lookAt = { 0.0, -1.0, 0.0 }.

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

Save the file and  test that everything is correct, by executing the simulation scenario.

```bash
>./LaunchServer.sh -f myFirstSenario.lua
```

The simulation must create the scene *Faculty_1floor* with the camera placed in the middle of the scene. In this case there are no agents, because none have been created. 

## Definition of  MASSIS agents

The creation of agents requires first the definition of some behavioural profile. 
We are going to use a profile for the agents that will move them from the student's cafeteria to classroom 1. We will name this profile *ReturnToClass*.

There are several built-in behaviours in MASSIS, which are located in the directory 
*massis3-examples-server/src/main/resources/CrowdBehaviors*:

* FollowingPathBehavior.bh
* FollowingPathWithSpeedBehavior.bh
* RunAwayBehavior.bh
* SecurityBehavior.bh
* ThiefBehavior.bh

In this example we will work with the *FollowingPathBehavior*. If you open the behavior file, you can see the name of the behavior in the attribute Name, in this case the name is *FollowingPath*, and the description of the behaviour, which in this case is made as a finite state machine (type="FSM") .

Edit the scenario description file and define a profile with the name *ReturnToClass* and set its behavior = "FollowingPath". Configure the SpeedMin and SpeedMax as you want and set the AnimationSpeedReference = 4.0 (See code example). Finally in RewriteParameter set the variable Path to "Class1".

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

## Creating an agents with a command

Now is the time to create the first agent. In the *Commands* section, add a *createHuman()* command:

```LUA
Commands:
MassisLua.createHuman("ReturnToClass", 1, "StudentsCafeteria")
```

This command creates an agent at the *StudentsCafeteria* position (at the centre of this area) with the behaviour *ReturnToClass*. When the simulation is launched, we can see that the agent is instantiated in the students' cafeteria, and walks to the classroom 1. The path that connects the cafeteria with the classroom is calculated automatically by the system.






[back to main](index.md)
