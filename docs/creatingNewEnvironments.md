# Creating new environments

## Introduction

The system allows adding new environments to be used in the simulations. In this section of the tutorial, we are going to add a space station as a new scenario and we try to rescue the crew of the station.

## Adding the scene model

To add a new scene we using Sweet Home 3D to create the scene model. In this tutorial we provide the scene already created in the Sweet Home 3D that you can download from **here**.

Next to download it, open the scene using Sweet Home 3D and save in the massis3-assets/Scenes with the name **SpaceStationTutorial.lua**.

In Sweet Home 3D, we are going to edit the name of the rooms. That it is important because it is easier to use names instead of numerical positions to specify agent positions in the scenario. 

We can modify the name of the room doing a double click and type the name in the pop-up window "Modify Rooms". After adding all the room names, save changes and then we will create the scene description in LAU. At first we only describe the scen and the camera position.


```LUA
Scenario = {
    Scene="tutorial",
    CameraConfig = {
        location = {347.0, 278.0, 161.0},
        rotation = {90.0,0.0,0.0},
        lookAt = {347.0,0.0,161.0}
    }
}
```


The first time to launch the new scene the system calculate the navmesh automatically. For that reason, the first execution takes more time than the rest.

To test that all is correct, run the escen with the next command:

```bash
> ./LaunchServer.sh -f EntranceToClassDifferentWaves.lua
```

Now we are going to create two different human profiles: **Crew** and **Rescuer**. The Crew stay in a room until detect a rescuer. In this moment, the agent follow the rescuer.

The Rescuer wander arrond the station find the crew agent and finally return to the initial point where is the rescue ship.


The bahavior **Crew** has two state. The initial state wait until the state change with the condition **IsCloseTo**. This condition check if the target stay at less distance to indicated in its input parameter.

The target is selecte with the perception **AgentWithTargetPerception** that select the nearest agent with the tag set in the parameter **Tag**.

```LUA
Behavior={
    Name="Crew",
    Type="FSM",
    Blackboard={
        Inputs={
            Target=nil,
        }
    },
    Perception={
        Action="AgentWithTargetPerception",
        Parameters={
            Inputs= {
                Tag="Rescuer",
                Distance=20
            },
            Outputs={
                Target="Blackboard.Target"
            }
        }
    },
    States={
        State0={
            Action="WaitForever",
            Initial=true,
        },
        State1={
            Action="Chase",
            Parameters={
                Inputs={
                Target="Blackboard.Target",
                ReevaluationTime=5.0,
                Animation="walk1",
                Distance=1.0
                }
            }
        }
    },
    Conditions={
        IsCloseTo={
            Inputs={
                Target="Blackboard.Target",
                Distance=2
            }
        }
    },
    Transitions={
        {
            From="State0",
            When="IsCloseTo",
            To="State1"
        }
    }
}
```

```LUA
Scenario = {
    Scene="tutorial",
    CameraConfig = {
        location = {347.0, 278.0, 161.0},
        rotation = {90.0,0.0,0.0},
        lookAt = {347.0,0.0,161.0}
    },
    AgentsDescriptions = {
        Crew = {
            behavior = "Crew",
            Speed = 2.0,
            tag = "Crew"
        },
        Rescuer = {
            behavior = "FollowingPathAgent",
            Speed = 2.0,
            tag = "Rescuer",
            RewriteParameter = {
                Path = "TLRoomCenter,TRRoomCenter,BRRoomCenter,BLRoomCenter",
                IsCircularPath = true,
                StoppingDistance=4
            }
        }
    }
}

Commands:

MassisLua.createHuman("Crew", 1, "TLRoomCenter")
MassisLua.createHuman("Crew", 1, "TRRoomCenter")
MassisLua.createHuman("Crew", 1, "BRRoomCenter")
MassisLua.createHuman("Crew", 1, "BLRoomCenter")
MassisLua.createHumanDeferred("Rescuer", 10, "TLRoomCenter", 5)

```