package com.massisframework.massis3.examples.simulation.SimulationDescriptions;

import com.massisframework.massis3.Convert;
import com.massisframework.massis3.core.config.AgentConfig;
import com.massisframework.massis3.core.config.AgentDescription;
import com.massisframework.massis3.core.config.SimulationExecutionConfig;
import com.massisframework.massis3.examples.simulation.LaunchServer;
import com.massisframework.massis3.helpers.HumanDescriptionMap;
import com.massisframework.massis3.services.dataobjects.JsonPoint;
import com.massisframework.massis3.services.eventbus.Massis3ServiceUtils;
import com.massisframework.massis3.services.eventbus.SimulationServerService;
import com.massisframework.massis3.services.eventbus.sim.HumanAgentService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PreconfiguredSimulation extends AbstractVerticle
{
    protected static final Logger log = LoggerFactory.getLogger(LaunchServer.class);

    private static final PreconfiguredSimulation[] _preconfiguredSimulations = {
            new SimulationFaculty_1floor(),
            new EnterToClassFaculty_1floor()
    };

    private static Map<String,PreconfiguredSimulation> _simulationMap = null;


    public static PreconfiguredSimulation getSim(String name)
    {
        Build();
        return _simulationMap.get(name);

    }

    public static List<String> getList()
    {
        Build();
        List<String> list = new ArrayList<>();
        for(int i = 0; i < _preconfiguredSimulations.length; ++i)
        {
            list.add(_preconfiguredSimulations[i].getSimulationName());
        }
        return list;
    }

    protected static void Build()
    {
        if(_simulationMap == null)
        {
            _simulationMap = new HashMap<>();
            for(int i = 0; i < _preconfiguredSimulations.length; ++i)
            {
                _simulationMap.put(_preconfiguredSimulations[i].getSimulationName(), _preconfiguredSimulations[i]);
            }
        }
    }

    protected void CreateHumans(long simID, SimulationExecutionConfig simConfig)
    {
        AgentConfig humanConfig = simConfig.getScenarioConfig().getAgentConfig();
        List<String> commands = simConfig.getScenarioConfig().getCommands();
        HumanDescriptionMap humanDescriptionMap = new HumanDescriptionMap(humanConfig.getAgentDescription());
        for(int i = 0; i < commands.size(); ++i)
        {
            String command = commands.get(i);
            processCommand(Long.toString(simID),command, humanDescriptionMap);
        }
    }

    protected void processCommand(String id, String command,HumanDescriptionMap humanDescriptionMap)
    {
        try {
            if (command.trim().startsWith("create")) {
                String[] parameters = command.split(" ");
                String populationID = parameters[1].trim();
                int numInstances = Integer.parseInt(parameters[2].trim());
                if(parameters.length == 4) {
                    //JsonPoint point = Convert.stringToJsonPoint(command);
                    createCommand(id, populationID, numInstances, parameters[3].trim(), humanDescriptionMap); //TODO es un future, corregirlo
                }
                else
                {
                    float time = Float.parseFloat(parameters[4].trim());
                    createDeferredCommand(id, populationID, numInstances, parameters[3].trim(),time, humanDescriptionMap); //TODO es un future, corregirlo
                }
            }
        }
        catch(Exception e)
        {
            log.error(" processCommand error: "+e.getMessage());
            e.printStackTrace();
        }
    }

    protected void createDeferredCommand(String id, String populationId, int numInstances, String point, float time, HumanDescriptionMap humanDescriptionMap)
    {
        AgentDescription humanDescription = humanDescriptionMap.get(populationId);
        for(int i = 0; i < numInstances; ++i) {
            HumanAgentService agentService = Massis3ServiceUtils.createProxy(vertx, HumanAgentService.class, id);
            agentService.createDeferredHumanWithDescriptionWithStrPoint(point,time,humanDescription.toJson(),ar2 -> { });
        }
    }

    protected void createCommand(String id, String populationId, int numInstances, String point, HumanDescriptionMap humanDescriptionMap)
    {
        AgentDescription humanDescription = humanDescriptionMap.get(populationId);
        for(int i = 0; i < numInstances; ++i) {
            HumanAgentService agentService = Massis3ServiceUtils.createProxy(vertx, HumanAgentService.class, id);
            agentService.createHumanWithDescriptionWithStrPoint(point,humanDescription.toJson(),ar2 -> { });
        }
    }
    protected void createCommand(String id, String populationId, int numInstances, JsonPoint point, HumanDescriptionMap humanDescriptionMap)
    {
        AgentDescription humanDescription = humanDescriptionMap.get(populationId);
        for(int i = 0; i < numInstances; ++i) {
            HumanAgentService agentService = Massis3ServiceUtils.createProxy(vertx, HumanAgentService.class, id);
            agentService.createHumanWithDescription(point,humanDescription.toJson(),ar2 -> { });
        }
    }

    protected Future<Long> CreateSimWithJson(io.vertx.core.json.JsonObject configuration)
    {
        SimulationServerService proxy = Massis3ServiceUtils.createProxy(vertx,SimulationServerService.class, Massis3ServiceUtils.GLOBAL_SERVICE_GROUP);
        Future<Long> simCreateFuture = Future.future();
        proxy.createWithJson(configuration,simCreateFuture);
        return simCreateFuture;
    }

    public abstract String getSimulationName();
    public abstract String getClassName();
}
