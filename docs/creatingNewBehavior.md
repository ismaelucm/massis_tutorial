# Creating new behaviors in MASSIS

[back to main](index.md)

## Before starting

The main directory where the system reads the files that describe the different behaviors for this tutorial is:

```
massis3-examples-server/src/main/resources/CrowdBehaviors
```

A new behavior is defined in a file with extension  **.bh** (behavior), which  must be put  in this directory. 


## Creating a new behavior file

Create a text file with the extension *.bh* and open it with your favorite editor (vi, gedit, sublime, etc.)


```bash
cd massis3-examples-server/src/main/resources/CrowdBehaviors/
> vi ArriveWaitLeaveBehavior.bh
```

## Defining the behavior skeleton using LUA

Start with a skeleton of a behavior, which consists of several parts  as shown below.

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

The system is created with the aim to have more types of behaviour formalisms in the future. But at this moment, the only behaviour type available is **FSM**.

FSM is an acronym for **Finite State Machine**. It is a behaviour representation formalism commonly used in  simulations, as autonomous behaviour in robotics and video-games, because it is relatively easy to understand. For this reason, it has been used in MASSIS as the principal method to create behaviours.

The FSM specifies a set of states that can execute actions. Only one state is running at the same time. Each state has a collection of events that allow making a transtion from the current state to another. That occurs when the condition that triggers the transition is satisfied.

## Creating the states

We are going to create a behaviour that moves the agent to a location,  waits a certain time, and finally leaves the faculty building by one of its doors.

This is done with 4 states, where the agent will execute the following actions:

* State0: Execute the action "NextPathPointAgent". This is the initial state of the FSM. 
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

* NextPathPoint: this action  sets in its output parameter *FinalPosition* the next value of the path list set in the input parameter *Path*.
    * inputs: 
        * Path: A list of locations,  separated by commas, that represent the path that the agent must follow.
        * Circular: A boolean that indicates,  when the path is finished, whether the action has to restart  from the beginning.
    * outputs:
        * FinalPosition: The next step of the path.
* MoveTo: this action  moves the agent to a position, which has been previously defined in the parameter *Target*, and playing the animation that is specified in the parameter *Animation*
    * inputs:
        * Target: The destination of the movement.
        * Animation: The animation that is executed when moving.
* Wait: this action  spends the time set in the parameter *Time* without doing anything.
    * inputs:
        * Time: The waiting time for the action.
* RunAway: this action randomly selects one of the locations that are specified in the parameter *PlaceList*.
    * inputs:
        * PlaceList: A list of the positions,  separated by commas, of possible destinations for the movement.


Now, we need to connect the input and output parameters of different actions to allow the correct data flow among them. For instance, the action *MoveTo* requires the destination location of the movement. The path that the agent follows is in the action *NextPathPoint*, which returns the next step of the path. To communicate the output value of the action *NextPathPoint* with the input parameter of the action *MoveTo* we are using the behaviour's blackboard.

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


The value of the parameter "Blackboard.Target" refers to a parameter "Target" of the blackboard. The action *NextPathPoint* writes  the next step of the path in the variable Path of the behaviour blackboard. The action *MoveTo* reads the value from the Path variable and copies the value in its input parameter *Target*.

Note that the input parameter of the action and the value of the variable on the blackboard do not have to match.

*NextPathPoint* requires two input parameters: Path and Circular. Path reads the values from the blackboard. Doing this, we delegate the value of the path when we use the behavior in the scenario. The variable *Circular* is False and in the action *MoveTo*, the variable *Animation* is "walk1"

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


The action *Wait* and *RunAway* also read their input values from the blackboard and delegate their values to the instantiation of the behavior in some manner to the action *NextPathPoint*.

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

Finally, we need to establish the transitions between the states. The *NextPathPoint*  transits to *MoveTo* when the action is finished. MoveTo also transits to Wait when is finished and so on. The final behaviour is shown below.

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

To test the behaviour, we are going to create a new scenario that uses the behaviour. This only has one agent that walks from the MainGate to the Classroom 1. The agent waits 2 seconds and leaves the faculty building by one of its two exits: the main or the back one.

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

These examples show how to configure the inputs parameters of the behaviour when it is used in a scenario. The path only has one step: Class1. The time to wait when the agent arrives at class1 is 2 seconds and the final destination of the agent, where the agent leaves the faculty building, will be either the main gate or the back gate. The action selects one of them randomly.

## Launch de scenario


Save the scenario as testBehavior.lua and execute it with the following command:

```bash
>./LaunchServer.sh -f testBehavior.lua
```

[back to main](index.md)
