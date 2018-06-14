# Trying several simulation scenarios with MASSIS

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

The following examples use as environment the model of the building of our Faculty of Computer Science.

### Example SimpleExample01.lua

In this simple simulation   one pedestrian is created outside the faculty, and moves to the first classroom. The walk speed of the agent is selected randomly between 1 - 5 and  uses the behaviour *FollowingPath*. This behavior moves the agente through a given path.

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

This scenario describes a behaviour profile named *population* that used the behaviour **FollowingPath** and its speed is a random value between 1 to 5. The parameter path of the behaviour is equal to *class1* and it is the destination of the agent. In the command section, the scenario creating one agent with the profile *population* at "MainGate" position.

To launch the example, the following command is needed

```bash
> ./LaunchServer.sh -f SimpleExample01.lua
```

### Example SimpleExample02.lua

This simulation creates two groups of pedestrians that walk at the first classroom, one of this at the main gate and another at the back. Both walk with random speeds between 1-5. All they use the same behaviour: FollowingPath.

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

The main difference between the previous example is the number of instantiations. In this case, the method createHuman() instantiate 20 agents at "MainGate" and other 20 instances at "BackGate".


To launch the example, the following command is needed

```bash
> ./LaunchServer.sh -f SimpleExample02.lua
```

### Example EntranceToclass.lua

This simulation creates ten groups of agents, five of them at the main gate and another five at the back gate. All groups walk with different speeds between 1-5. All they use the same behaviour: FollowingPath. Each group have a different classroom as a destination. 

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

To launch the example, the following command is needed

```bash
> ./LaunchServer.sh -f EntranceToclass.lua
```

### Example EntranceToClassDifferentWaves.lua


The simulation has two agent waves. In the first wave, the simulation creates twelve groups of agents, six of them in the main gate and another six in the back gate. Each group have a different classroom as a destination. The second wave is the same as the first wave but start 50 seconds later. All they use the behaviour FollowingPath.


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

We have used the command *createHumanDeferred* creating agents with a delay (50 seconds in this case). This allows the second wave of agents.

To launch the example, the following command is needed

```bash
> ./LaunchServer.sh -f EntranceToClassDifferentWaves.lua
```


### Example Evacuation.lua

The simulation simulates an evacuation of the five classrooms. The path will follow the agents is set in the profiled. 
* The profilers: Class1, Class2 and Class3, are evacuated through the main door.
* The profilers: Class4 and Class5, are evacuated through the back door.
* The profiler Cafeteria is evacuated through the main door.


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

We have used a guided path that the agents must follow. For each profile, the path change. 

To launch the example, the following command is needed

```bash
> ./LaunchServer.sh -f Evacuation.lua
```

[back to main](index.md)
