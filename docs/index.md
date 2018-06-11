# MASSIS3 TUTORIAL


Welcome to MASSIS V3 tutorial.

# Description

**MASSIS** is a software framework and a tool to facilitate the creation of 3D simulations in indoor environments (i.e., inside a building). This framework allows the creation of multiple agents with different behaviours. The users can reuse this behaviour in other agents involves in other simulation scenarios. The system has tools to create easily new simulation environments that we can use in our simulations after.

MASSIS has been designed to keep a balance between flexibility and performance.

<!-- The next figure shows the MASSIS' software architecture. -->

## Massis funtionality

* Creating simulation in 3D of different agent behaviours in indoor environments.* Agent behaviours are implemented using a Hierarchical Finite State Machine formalism.
* Supports different forms to describe the behaviour used by the agents, i.e:  LUA, JSON and programmatically.
* There is a set of predefined behaviour and primitives to make your own.
* The set of primitives can be extended, creating your own programmatically.
* A 3D editor has been incorporated to make new 3d environments easily.
* Some simulation scenarios are provided as examples.

## What's new

In the last version (3.4) we have adding new features and improvements. Among other things it has been modified:

* Pathfinding algorithms have been improved from previous versions.
* Walking and running speed now can be modified in real time. 
* Now it is easier to create new behaviours and new scenarios using LUA


# Purpose of the tutorial

The purpose of this tutorial shows step to step the process to create: from a simple simulations to more complex scenarios with the aim to demonstrate our tool.

# Table of Contents

* [Getting started](getting_started.md)
* [Some examples](Examples.md)
* [Creating new scenarios](creatingNewScenario.md)
* [Creating new behaviors](creatingNewBehavior.md)
* Appendix:
    * [Actions:](appendix_actions.md)
    * [Conditions:](appendix_conditions.md)
    * [Behaviours:](appendix_behaviours.md)


