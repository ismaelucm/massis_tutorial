# Appendix: Actions

This section describes the actions that are available in MASSIS by default. Actions are behaviour primitives that modify the environment state. For example moving the agent, playing an animation, etc.


## ChaseAction

The agent runs after  another. The parameter *Target* is the aim of the pursuit. Each certain time, the action reevaluates the path between the target and the agent. If the distance between both is lower than a value them the action is finished. 

### Input Parameters

```
Target: The target agent of the pursuit.
ReevaluationTime: Time period the agent re-evaluates the path between agent and target.
Animation: The animation that plays the agent during the persecution.
Distance: The distance from which the agent is considered to have reached the target.

```

## MoveTo

This action moves the agent to a position.


### Input Parameters

```
Target: The destination of the movement.
Animation: The animation that plays the agent during the movement.
AnimationSpeed: The speed that the agent plays the animation.

```

## NextPathPoint

This action chooses from the list of steps of the movement the next one and returns it.

### Input Parameters

```
Path: The list of steps, separated by commas.
Circular: If the parameter value is true, the action repeats the path, returning to the starting point when the path becomes to the end.

```


### Output Parameters
```
FinalPosition: The output value with the next position of the path or null if it is finished.
```


## PlayAnimation

This action plays the animation set in its input parameter.


### Input Parameters

```
Animation: The name of the animation that the action plays.

```


## RandomPoint

Selects a point in the space randomly.


### Output Parameters

```
finalPosition: The selected point.
```



## StopCurrentAnimation

Stop the animation that the agent plays when the action is running.

## WaitForever


Waits an infinite time.

## Wait

Waits the time set in its input parameter.

### Input Parameters

```
Time: The time to wait the agent in seconds.

```

