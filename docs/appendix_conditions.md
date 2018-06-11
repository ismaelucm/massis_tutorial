# Appendix: Conditions

In this section, we describe conditions available in MASSIS by default. 
Conditions check the value of certain variables. Its objective is changing the current state on the FSM.


## ActionFinisehd

This condition is triggered when the action that is running on the behaviour finished.


## ActionSuccess

This condition is triggered when the action that is running on the behaviour finished successfully.


## ActionFailure

This condition is triggered when the action that is running on the behaviour finished unsuccessfully.




## IsCloseTo

This condition return true when the target is is near to the agent.

### Input Parameters

```
Target: The agent with which the condition measures the distance.
Distance: The distance that the condition consider as close.

```


## IsSuspicious

This condition returns true when the agent identified with the *EntityId* sets in the input parameter has a level of suspicion grater tham the conditions allow. Note the target agent must be have the component *Personality* activated.

### Input Parameters

```
EntityId: The agent's id.
Threshold: the tolerable level of suspicion.

```