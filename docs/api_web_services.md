# API Web Services

## Introduction

The system allows getting information using web services to consult and manipulate the simulations. For instance, whether you can get the position of an agent with an HTTP query like this:

```
get http://[server]:[port]/api/simulations/[simID]/human-agent/allHumanInfo/
```

Where *server* and *port* are the IP of the machine and the port where MASSIS is running. 

*SimID* is the ID of the simulation. MASSIS can handler several simulations at the same time. Each simulation has an ID that identifies it. Consequently, when consulting something about a simulation using the Web API, you must specify the simulation ID which you refer.


## Some examples of Web API

If you running a simulation, stop it using control+c.

Run the example EntranceToClassDifferentWaves:

```bash
> ./LaunchServer.sh -f EntranceToClassDifferentWaves.lua
```

You mast remember where is the port where the MASSIS was configured. 

### Get postion of the agent

Next, we are going to consult the position of the agents with this commando:

```bash
curl http://localhost:8080/api/simulatons/0/human-agent/positions/
```

*server*, *port* and the *simulation id* (in the example 0) may be different depending on the one established in your local configuration.



```bash
http://localhost:8080/api/simulations/0/environment/camera/0/video
```