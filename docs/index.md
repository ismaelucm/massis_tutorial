# MASSIS3 TUTORIAL

Welcome to the MASSIS V3 tutorial.

# Description

**MASSIS** is a software framework that facilitates the creation of 3D agent-based simulations in indoor environments (i.e., inside a building). It allows the specification of a large number of agents with different behaviours. Agent behaviours can be reused for other agents  in other simulation scenarios. There are also tools to easily create  new  environments for the simulations.

MASSIS has been designed to keep a balance between flexibility and performance for large agent-based simulations.

<!-- The next figure shows the MASSIS' software architecture. -->

## Massis funtionality

* Creating a simulation in 3D with different agent behaviours in indoor environments.
* Agent behaviours are implemented using a Hierarchical Finite State Machine formalism.
* Support for different ways to describe the behaviour used by the agents, i.e:  LUA, JSON and programmatically.
* There is a set of predefined behaviour and primitives to make your own.
* The set of primitives can be extended, creating your own programmatically.
* A 3D editor has been incorporated to facilitate the creation of new 3D environments.
* Some simulation scenarios are provided as examples.

## What's new

The last version (3.4) has new features and improvements:

* Pathfinding algorithms have been improved from previous versions.
* Walking and running speed now can be modified in real time. 
* Now it is easier to create new behaviours and new scenarios using LUA.


# Purpose of the tutorial

This tutorial shows, step by step, the process to create MASSIS simulations,  from a simple simulation model to more complex scenarios.

# Table of Contents

* [Getting started](getting_started.md)
* [Some examples](Examples.md)
* [Creating new scenarios](creatingNewScenario.md)
* [Creating new behaviors](creatingNewBehavior.md)
* API web services:
    * [Creating new behaviors](api_web_services.md)
* Creating new environments:
    * [Creating new behaviors](creatingNewBehavior.md)
* Appendix:
    * [Actions:](appendix_actions.md)
    * [Conditions:](appendix_conditions.md)
    * [Behaviours:](appendix_behaviours.md)


