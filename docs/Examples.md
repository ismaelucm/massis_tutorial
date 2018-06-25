# Creating simulation scenarios in MASSIS

[back to main](index.md)

## How to define simulations

There are numerous manners to define a scenario in MASSIS, for instance, describing it using LUA or JSON format, even programmatically. But in this section, we explain only one of them: using LUA format because it is the most intuitive. 

A scenario is a LUA file that has two parts. First of them, the definition of the scenario and the second of them the command section.


```LUA
Scenario={
}

Commands:
MassisLua.Method(parameters...)
```

### Scenario Section

A scenario is a table in LUA that describes the simulation. 

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

* **Scene**: The name of the 3D environment that you want to simulate.
* **CameraConfig**: The initial configuration of the camera. In this section, we can define the position, rotation and the direction where the camera looks.
* **AgentsDescription**: In this section, we can describe the different behavioural profiles that using the simulation. Each of these profiles is a specific configuration of a behaviour selected. This behaviour governs the agent's decision making. Some attributes in this section are common to all agents, for instance, the min and max speed or the animation speed; but others (those that are in the subsection *RewriteParameter*) are specific to the behaviour selected.

### Command Section

The command section is used to invoke methods on the simulator. These methods can perform different actions. Nowadays, the commands implemented are:

* MassisLua.createHuman(behavioural-profile, num-instances, location): Create a number of human agents in a specific location.
    * behavior-profile: The behavioural profile used by the agent.
    * num-instances: Number of the instances of the agent created.
    * location: The location where the agent will be instantiated.
* MassisLua.createHumanDeferred(behavioural-profile, num-instances, location, delay): Similar to previous one, but the agent is created with a delay indicated in the fourth parameter.


Next, we will explain the different scenarios that MASSIS brings by already configured. These scenarios can be directly executed and serve as an example to create new ones.

## Examples included in MASSIS


MASSIS has some simulations and behaviours created as an example. In this section of the tutorial, we will explain each of them and how they have been configured.

### Example SimpleExample01.lua

This is a simple simulation where we creating one pedestrian outside the faculty, going to the first classroom. Its walk speed is selected randomly between 1 - 5 and it use the behaviour FollowingPath. This behavior move the agente down a given path.

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

[Back to main](index.md)
[Next](creatingNewScenario.md)