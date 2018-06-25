# Appendix: Behaviors

This section  describes the behaviours that are available in MASSIS by default. Behaviours are complex actions composed by other primitives or behaviours.  These behaviours can be used as an action in other behaviours or directly by the agent. 
The behaviours are LUA files, which are stored in the path: massis3-examples-server/src/main/resources/CrowdBehaviors/



## FollowingPath

This behavior moves the agent following a path that is specified  in its input parameter.


### Input Parameters

```
Path: The path that the agent must follow, separated by commas.
IsCircularPath=false: If the parameter value is true, the behaviour repeats the movement, returning to the starting point when the path becomes to the end.

```

## FollowingPathWithSpeed


This is the same as FollowingPath but the speed in each step of the path can be different.

### Input Parameters

```
Path: The path that the agents must follow, separates by commas.
SpeedPath: The diferents speed in each step of the path, separated by commas.
IsCircularPath=false: If the parameter value is true, the behaviour repeats the movement, returning to the starting point when the path becomes to the end.
```


## RunAway

From the list of possible destinations, select one randomly and moves the agent to the selected destination.


### Input Parameters

```
PlaceList: The list of possible destinations, separated by commas.
```


