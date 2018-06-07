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
* State1: Execute the action "MoveToAgent". 
* State2: Execute the action "Wait". 
* State3: Execute the action "RunAway". 


```LUA
Behavior={
    Name="",
    Type="FSM",
    Blackboard={
    },
    States={
        State0={
            Action="NextPathPoint",
            Initial=true,
        },
        State1={
            Action="MoveTo",
        },
        State2={
            Action="Wait",
        }
        State3={
            Action="RunAway",
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




## Using the behavior in a scenario


## Launch de scenario

[back to main](index.md)