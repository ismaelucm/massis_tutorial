# Examples of simulation scenarios with MASSIS

[back to main](index.md)

## How to define simulations

A scenario in MASSIS can be described in different ways, for instance, with specifications using LUA or JSON, or programmatically. This section shows one of these methods, using LUA format, because it is the most intuitive. 

A scenario can be defined in a LUA file with two parts. The first defines the scenario elements and the second some commands, for instance, to create some agents when starting the simulation.


```LUA
Scenario={
}

Commands:
MassisLua.method(parameters...)
```

### Elements of a Scenario 

The elements of a scenario are defined in a table in LUA, such as the following: 

```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 85.0, 100.0, 54.0 },
        rotation = { 90.0, 0.0, 0.0 },
        lookAt = { 85.0, 0.0, 54.0 }
    },
    AgentsDescriptions = {
        population = {
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
```

This table has the following sections:

* **Scene**: The name of the 3D environment  to simulate.
* **CameraConfig**: The initial configuration of a camera, which consists of its position, rotation and the direction where the camera focuses.
* **AgentsDescription**: Agents in the simulation have behavioural profiles. Each of these profiles is a specific configuration of a concrete behaviour, which governs the agent's decision making. Some attributes in this section are common to all agents, for instance, the min and max speed or the animation speed; but others (those that are in the subsection *RewriteParameter*) are specific to the behaviour that is defined.

### Commands

The commands section is used to invoke methods on the simulator. These methods can perform different actions. Nowadays, the commands that are implemented are:

* MassisLua.createHuman(behavioural-profile, num-instances, location): Create a number of human agents in a specific location.
    * behavior-profile: The behavioural profile used by the agent.
    * num-instances: Number of the instances of the agent that will be created.
    * location: The location where the agents will be instantiated.
* MassisLua.createHumanDeferred(behavioural-profile, num-instances, location, delay): Similar to previous one, but the agent is created with a delay that is indicated by the fourth parameter (in seconds).



## Examples included in MASSIS

The tutorial makes uses of several scenarios that are already provided with the MASSIS distribution. These scenarios can be directly executed and serve as  examples to create new ones.

The following examples use as environment the model of the building of our [Faculty of Computer Science](http://informatica.ucm.es/).

### Example SimpleExample01.lua

In this simple simulation   one pedestrian is created outside the faculty, and moves to the first classroom. 

The *Scenario* section specifies an   agent profile *populaton* with the behaviour *FollowingPath* and a walk speed that is randomly selected  between 1.0 and 5.0. This behavior moves the agent through a given path to a destination *Class1*.

```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 85.0, 100.0, 54.0 },
        rotation = { 90.0, 0.0, 0.0 },
        lookAt = { 85.0, 0.0, 54.0 }
    },
    AgentsDescriptions = {
        population = {
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
MassisLua.createHuman("population", 1, "MainGate")
```

The *Commands* section specifies that the scenario will start with the creation of one agent with the profile *population* at the *MainGate* position.

The example can be executed with the following command:

```bash
> ./LaunchServer.sh -f SimpleExample01.lua
```

### Example SimpleExample02.lua

This simulation creates two groups of pedestrians that walk to the first classroom, one of these start at the main gate and the other at the back of the building. Both walk with random speeds between 1.0 and 5.0. All of them use the same behaviour: *FollowingPath*.

```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 85.0, 100.0, 54.0 },
        rotation = { 90.0, 0.0, 0.0 },
        lookAt = { 85.0, 0.0, 54.0 }
    },
    AgentsDescriptions = {
        population = {
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
MassisLua.createHuman("population", 20, "MainGate")
MassisLua.createHuman("population", 20, "BackGate")
```

The main difference between the previous example is the number of instantiations. In this case, the method *createHuman()* instantiates 20 agents at *MainGate* and other 20 instances at *BackGate*.


The example can be executed with the following command:

```bash
> ./LaunchServer.sh -f SimpleExample02.lua
```

### Example EntranceToclass.lua

This simulation creates ten groups of agents, five  at the main gate and the other five at the back gate. All groups walk with different speeds between 1.0 and 5.0. All of them use the same behaviour: *FollowingPath*. Each group has a different classroom as  destination. 

```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 33.0, 50.0, 54.0 },
        rotation = { 90.0, 0.0, 0.0 },
        lookAt = { 0.0, 0.0, 0.0 }
    },
    AgentsDescriptions = {
        Class1 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class1"
            }
        },
        Class2 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class2"
            }
        },
        Class3 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class3"
            }
        },
        Class4 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class4"
            }
        },
        Class5 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class5"
            }
        }
    }
}
Commands:
MassisLua.createHuman("Class1", 25, "MainGate")
MassisLua.createHuman("Class1", 25, "BackGate")
MassisLua.createHuman("Class2", 20, "MainGate")
MassisLua.createHuman("Class2", 20, "BackGate")
MassisLua.createHuman("Class3", 15, "MainGate")
MassisLua.createHuman("Class3", 20, "BackGate")
MassisLua.createHuman("Class4", 25, "MainGate")
MassisLua.createHuman("Class4", 20, "BackGate")
MassisLua.createHuman("Class5", 25, "MainGate")
MassisLua.createHuman("Class5", 15, "BackGate")
```

The example can be executed with the following command:

```bash
> ./LaunchServer.sh -f EntranceToclass.lua
```

### Example EntranceToClassDifferentWaves.lua

This simulation has two agent waves. The first wave consists of twelve groups of agents, six of them are created at the main gate and other six at the back gate. Each group has a different classroom as  destination. The second wave is the same as the first wave but starts 50 seconds later. All of them use the behaviour *FollowingPath*.


```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 33.0, 165.0, 54.0 },
        rotation = { 90.0, 0.0, 0.0 },
        lookAt = { 0.0, 0.0, 0.0 }
    },
    AgentsDescriptions = {
        Class1 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class1"
            }
        },
        Class2 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class2"
            }
        },
        Class3 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class3"
            }
        },
        Class4 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class4"
            }
        },
        Class5 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class5"
            }
        },
        Cafeteria = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "StudentsCafeteria"
            }
        }
    }
}
Commands:
MassisLua.createHuman("Class1", 10, "MainGate")
MassisLua.createHuman("Class1", 10, "BackGate")
MassisLua.createHuman("Class2", 10, "MainGate")
MassisLua.createHuman("Class2", 10, "BackGate")
MassisLua.createHuman("Class3", 10, "MainGate")
MassisLua.createHuman("Class3", 10, "BackGate")
MassisLua.createHuman("Class4", 10, "MainGate")
MassisLua.createHuman("Class4", 10, "BackGate")
MassisLua.createHuman("Class5", 10, "MainGate")
MassisLua.createHuman("Class5", 10, "BackGate")
MassisLua.createHuman("Cafeteria", 10, "MainGate")
MassisLua.createHuman("Cafeteria", 10, "BackGate")

MassisLua.createHumanDeferred("Class1", 10, "MainGate", 50)
MassisLua.createHumanDeferred("Class1", 10, "BackGate", 50)
MassisLua.createHumanDeferred("Class2", 10, "MainGate", 50)
MassisLua.createHumanDeferred("Class2", 10, "BackGate", 50)
MassisLua.createHumanDeferred("Class3", 10, "MainGate", 50)
MassisLua.createHumanDeferred("Class3", 10, "BackGate", 50)
MassisLua.createHumanDeferred("Class4", 10, "MainGate", 50)
MassisLua.createHumanDeferred("Class4", 10, "BackGate", 50)
MassisLua.createHumanDeferred("Class5", 10, "MainGate", 50)
MassisLua.createHumanDeferred("Class5", 10, "BackGate", 50)
MassisLua.createHumanDeferred("Cafeteria", 10, "MainGate", 50)
MassisLua.createHumanDeferred("Cafeteria", 10, "BackGate", 50)
```

We have used the command *createHumanDeferred* to create agents with a delay (50 seconds in this case). This allows the second wave of agents.

The example can be executed with the following command:

```bash
> ./LaunchServer.sh -f EntranceToClassDifferentWaves.lua
```


### Example Evacuation.lua

This example shows an evacuation of the five classrooms. The path that the agents will follow  is defined with the profiles:
* The profiles Class1, Class2 and Class3  are evacuated through the main door.
* The profiles Class4 and Class5  are evacuated through the back door.
* The profile  Cafeteria is evacuated through the main door.


```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 61.0, 100.0, 40.0 },
        rotation = { 90.0, 0.0, 0.0 },
        lookAt = { 61.0, 0.0, 40.0 }
    },
    AgentsDescriptions = {
        Class1 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class1,LobbyEventRoom.Hall,MainHall.Entrance,MainGate"
            }
        },
        Class2 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class2,ClassesLobby.Class1,LobbyEventRoom.Hall,MainHall.Entrance,MainGate"
            }
        },
        Class3 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class3,ClassesLobby.Class2,ClassesLobby.Class1,MainHall.Entrance,MainGate"
            }
        },
        Class4 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class4,ClassesLobby.Class5,HallBackGate.Lobby,BackGate"
            }
        },
        Class5 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class5,HallBackGate.Lobby,BackGate"
            }
        },
        Cafeteria = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "MainGate"
            }
        }
    }
}
Commands :
MassisLua.createHuman("Class1", 30, "Class1")
MassisLua.createHuman("Class2", 30, "Class2")
MassisLua.createHuman("Class3", 30, "Class3")
MassisLua.createHuman("Class4", 30, "Class4")
MassisLua.createHuman("Class5", 30, "Class5")
MassisLua.createHuman("Cafeteria", 30, "ProfessorsCafeteria")
```

In this example the agents are following  a guided path, which changes for each profile. 

The example can be executed with the following command:

```bash
> ./LaunchServer.sh -f Evacuation.lua
```

<<<<<<< HEAD
[Back to main](index.md)
[Next](creatingNewScenario.md)
=======
[back to main](index.md)
>>>>>>> 7aa40aed4cef7b88b29409ade2c2af1d056b6199
