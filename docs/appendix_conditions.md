# Appendix: Conditions

This section describes the conditions available in MASSIS by default. 
Conditions check the value of certain variables. Its objective is changing the current state on the FSM.


## ActionFinisehd

This condition is triggered when the action that is running on the behaviour has finished.


## ActionSuccess

This condition is triggered when the action that is running on the behaviour has successfully finished.


## ActionFailure

This condition is triggered when the action that is running on the behaviour has unsuccessfully finished.


## IsCloseTo

This condition returns true when the target  is near to the agent.

### Input Parameters

```
Target: The agent with which the condition measures the distance.
Distance: The distance that the condition considers as close.

```


## IsSuspicious

This condition returns true when the agent identified with the *EntityId* sets in the input parameter has a level of suspicion greater tham the conditions allow. Note that the target agent must  have the component *Personality* activated.

### Input Parameters

```
EntityId: The agent's id.
Threshold: The tolerable level of suspicion.

```
