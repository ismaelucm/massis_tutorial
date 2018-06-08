# Creating new behavior in MASSIS

[back to main](index.md)

## Before starting

The directory when the system read the files that describe the different behavior is:

massis3-examples-server/src/main/resources/CrowdBehaviors

If a new behavior is created, you must put the file in this directory. The behaviour files have to the extension .bh (behavior)


## Creating a new behavior file

Create a text file with the extension bh and open with your favorite editor.


```bash
cd massis3-examples-server/src/main/resources/CrowdBehaviors/
> touch ArriveWaitLeaveBehavior.bh
```

## Defining the behavior skeleton using LUA

Then we will create the skeleton of a behavior with the different parts that make it up as shown below.

```LUA
Behavior={
    Name="",
    Type="FSM",
    Blackboard={
    },
    States={
    },
    Transitions={
    }
}
```

## Finite State Machines (FSMs)

The system is created with the aim to have more types of behaviour formalisms in the future. But at this moment, the only behaviour type available is FSM.

FSM is the acronyms of Finite State Machine. It is a behaviour representation formalism widely used in the simulation, as autonomous behaviour in robotics and video-games. This formalism is easy to understand with non-technically users. For this reason, it has been used in MASSIS as the principal method to create behaviours.

The FSM is a set of states that can execute actions. Only one state is running at the same time. Each state has a collection of events that permit changing the current state to another. That occurs when the condition that triggers the transition is satisfied.

## Creating the states

We are going to create a behaviour that moves the agent at a location, them wait a certain time and finally leave the faculty by one of its doors.

We are going to add 4 states to the behaviour that execute the following actions:

* State0: Execute the action "NextPathPointAgent" and its the initial state of the FSM. 
* State1: Execute the action "MoveTo". 
* State2: Execute the action "Wait". 
* State3: Execute the action "RunAway". 


```lua
Behavior={
    Name="ArriveWaitLeave",
    Type="FSM",
    Blackboard={
    },
    States={
        State0={
            Action="NextPathPoint",
            Initial=true
        },
        State1={
            Action="MoveTo"
        },
        State2={
            Action="Wait"
        }
        State3={
            Action="RunAway"
        }
    },
    Transitions={
    }
}
```

The actions used as explained below:


* NextPathPoint: is an action that sets in its output parameter *FinalPosition* the next value of the path list set in the input parameter *Path*.
    * inputs: 
        * Path: A list of locations,  separated by commas, that represent the path that must follow the agent.
        * Circular: boolean that indicates the action that when the path is finished, restart it from the beginning.
    * outputs:
        * FinalPosition: The next step of the path.
* MoveTo: is an action that moves an agent to a position, previously set in the parameter *Target* and playing the animation set in the parameter *Animation*
    * inputs:
        * Target: The destination of the movement.
        * Animation: The animation that is executed when moving.
* Wait: is an action that spends the time set in the parameter *Time* without doing anything.
    * inputs:
        * Time: The waiting time for the action.
* RunAway: is an action that selects one of the locations sets in the parameter *PlaceList* randomly.
    * inputs:
        * PlaceList: A list of the positions,  separated by commas, from the action the selects the destination of the movement.


Now, we need to connect the input and output parameters of different actions to allow the correct data flow among them. For instances, the action *MoveTo* need the destination location of the movement. The path that the agent follows is in action *NextPathPoint* that return the next step of the path. To communicate de output value of the action *NextPathPoint* with the input parameter of the action *MoveTo* we using the behaviour's blackboard.

```lua

    Blackboard={
        Inputs={
            Target=""
        }
    },
    States={
        State0={
            Action="NextPathPoint",
            Initial=true,
            Outputs={
                FinalPosition="Blackboard.Target"
            }
        },
        State1={
            Action="MoveTo",
            Inputs={
                Target="Blackboard.Target",
            }
        },
```


The value of the parameter "Blackboard.Target" refers to a parameter "Target" of the blackboard. The action *NextPathPoint* write in the variable Path of the behaviour blackboard the next step of the path. The action MoveTo read the value from the Path variable and copy the vlue in its input parameter *Target*.

Note that the input parameter of the action and the value of the variable on the blackboard do not have to match.

*NextPathPoint* need two input parameters: Path and Circular. Path reads the values from the blackboard. Doing this, we delegate the value of the path when we use the behavior in the scenario. The variable *Circular* is False and in the action *MoveTo*, the variable Animation is "walk1"

```lua

    Blackboard={
        Inputs={
            Target="",
            Path=""
        }
    },
    States={
        State0={
            Action="NextPathPoint",
            Initial=true,
            Inputs={
                Path="Blackboard.Path",
                Circular=false
            },
            Outputs={
                FinalPosition="Blackboard.Target"
            }
        },
        State1={
            Action="MoveTo",
            Parameters={
                Inputs={
                    Target="Blackboard.Target",
                    Animation="walk1"
                }
            }
        }
```


The action *Wait* and *RunAway* aso read theirs inputs values from the blackboard and delegate theirs values to the instanciation of the behavior in some manner to the action *NextPAthPoint*.

```LUA

    Blackboard={
        Inputs={
            Target="",
            Path="",
            Time="",
            PlaceListToRunAway=""
        }
    },
    States={
        State0={
            Action="NextPathPoint",
            Initial=true,
            Parameters={
                Inputs={
                    Path="Blackboard.Path",
                    Circular=false
                },
                Outputs={
                    FinalPosition="Blackboard.Target"
                }
            }
        },
        State1={
            Action="MoveTo",
            Parameters={
                Inputs={
                    Target="Blackboard.Target",
                    Animation="walk1"
                }
            }
        },
        State2={
            Action="Wait",
            Parameters={
                Inputs={
                    Time="Blackboard.Time",
                }
            }
        },
        State3={
            Action="RunAway"
            Parameters={
                Inputs={
                    PlaceList="Blackboard.PlaceListToRunAway"
                }
            }
        }
    }
```


And finally, we need establishing the transitions between the states. The NextPathPoint transits to MoveTo when the action is finished. MoveTo also transits to Wait when is finished and so on. The final behaviour is shown below.

```LUA
Behavior={
    Name="ArriveWaitLeave",
    Type="FSM",
    Blackboard={
        Inputs={
            Target="",
            Path="",
            Time="",
            PlaceListToRunAway=""
        }
    },
    States={
        State0={
            Action="NextPathPoint",
            Initial=true,
            Inputs={
                Path="Blackboard.Path",
                Circular="Blackboard.IsCircularPath"
            },
            Outputs={
                FinalPosition="Blackboard.Target"
            }
        },
        State1={
            Action="MoveTo",
            Parameters={
                Inputs={
                    Target="Blackboard.Target",
                    Animation="walk1"
                }
            }
        },
        State2={
         Action="Wait",
         Parameters={
             Inputs={
                 Time="Blackboard.Time",
             }
         }
        },
        State3={
         Action="RunAway"
         Parameters={
             Inputs={
                 PlaceList="Blackboard.PlaceListToRunAway"
             }
         }
        }
    },
    Transitions={
        {
         From="State0",
         When="ActionFinish",
         To="State1"
        },
        {
         From="State1",
         When="ActionFinish",
         To="State2"
        },
        {
         From="State2",
         When="ActionFinish",
         To="State3"
        },
        {
         From="State3",
         When="ActionFinish",
         To="State4"
        }
    }
}
```

## Using the behavior in a scenario


To test the behaviour, we are going to create a new scenario using the behaviour. The scenario only has one agent that walk from the MainGate to the Classroom 1, it waits 2 seconds and leaves the faculty by one of its two exits: the main or the back one.

```LUA
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 70.0, 100.0, 55.0 },
        rotation = { 90.0,0.0,0.0 },
        lookAt = { 0.0, -1.0, 0.0 }
    },
    AgentsDescriptions = {
        Agent01={
            behavior = "ArriveWaitLeave",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class1",
                Time=2.0,
                PlaceListToRunAway="MainGate,BackGate"
            }
        }
    }
}
Commands:
MassisLua.createHuman("Agent01", 1, "MainGate")
```

Teh examples shows how configure the inputs parameters of the behavior when it is used in a scenario. The path only have one step: Agent01. The time to wait when the agent arrive at class1 is 2 seconds and the final destination of the agent, where the agent leaves the faculty, will be either the main gate or the back gate.

## Launch de scenario


Save the scenario as testBehavior.lua and launch:

```bash
>./LaunchServer.sh -f testBehavior.lua
```

[back to main](index.md)