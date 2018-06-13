# Appendix: Behaviors

In this section, we describe the behaviours available in MASSIS by default. Behaviours are complex actions composed by other primitives or behaviours.  These behaviours can be used as an action en other behaviours or directly in the agent. The behaviours are LUA files stored in the path: massis3-examples-server/src/main/resources/CrowdBehaviors/



## FollowingPath

This behavior move the agent following a path sets in its input parameter.


### Input Parameters

```
Path: The path that the agents must follow, separates by commas.
IsCircularPath=false: If the parameter value is true, the behaviour repeats the movement, returning to the starting point when the path becomes to the end.

```

## FollowingPathWithSpeed


Is the same of FollowingPath but the speed in each stretch of the path can be different.

### Input Parameters

```
Path: The path that the agents must follow, separates by commas.
SpeedPath: The diferents speed in each stretch of the path, separates by commas.
IsCircularPath=false: If the parameter value is true, the behaviour repeats the movement, returning to the starting point when the path becomes to the end.
```


## RunAway

From the list of possible destination, select one randomly and moves the agent at the selected.


### Input Parameters

```
PlaceList: The list of possible destinations, separated by commas.
```


