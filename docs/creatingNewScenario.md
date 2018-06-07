# Creating new scenarios in MASSIS

[back to main](index.md)

## Before starting

The main directory when the system read the files that describe the different scenarios is:

 massis3-examples-server/src/main/resources/simulationExamples/

 If the new scenarios are created in this directory, is not needed to specify the file path.  The name of the file is enough. Otherwise, the name of the file must be preceded by the relative path, from the root directory (usually *massis3-4-examples*).

## Creating the LUA file

Create a text file with the extension lua and open with your favorite editor.


```bash
> touch myFirstSenario.lua
```

## Choose the different behaviors used and the profilers


Then we will create the skeleton of a scenario with the different parts that make it up as shown below.

There are several behaviours pre-made in MASSIS in the directory *CrowdBehaviors*.

* FollowingPathBehavior.bh
* FollowingPathWithSpeedBehavior.bh
* RunAwayBehavior.bh
* SecurityBehavior.bh
* ThiefBehavior.bh

In this example we selected RunAwayBehavior. If you open the behavior, you must see the name of the behavior in the attribute Name. In this case the name is RunAway.

## Creating the MASSIS commands


## Launch de scenario

[back to main](index.md)