package com.massisframework.massis3.examples.simulation.SimulationDescriptions;

import com.massisframework.massis3.core.config.*;
import com.massisframework.massis3.examples.simulation.LancherDefs;
import com.massisframework.massis3.examples.simulation.SimulationDescriptions.templates.DummySimulationExecutionConfigTemplate;
import com.massisframework.massis3.services.dataobjects.JsonPoint;
import com.massisframework.massis3.services.eventbus.sim.HumanAgentService;
import com.massisframework.massis3.simulation.server.SimulationServerLauncher;
import io.vertx.core.Future;

import java.util.Arrays;

public class SimulationFaculty_1floor extends PreconfiguredSimulation
{

    public String getSimulationName()
    {
        return "Faculty_1floor";
    }

    public String getClassName()
    {
        return SimulationFaculty_1floor.class.getName();
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception
    {
        io.vertx.core.json.JsonObject config = this.config();
        String ip = config.getString(LancherDefs.IP);
        int port = config.getInteger(LancherDefs.PORT).intValue();
        String assetPath  = config.getString(LancherDefs.ASSETS_PATH);
        // 1. Deploy simulation server verticle
        // cfg=JsonObject.mapFrom(this.config());
        SimulationServerConfig cfg = new SimulationServerConfig()
                .withAssetFolders(Arrays.asList(
                        assetPath+"Scenes",
                        assetPath+"models",
                        assetPath+"animations"))
                .withHttpServerConfig(
                        new HttpServerConfig().withHost(ip).withPort(port))
                .withAuthPropertiesFile("classpath:webauth.properties")
                .withInstances(1)
                .withRendererType(SimulationServerConfig.RendererType.LWJGL_OPEN_GL_3)
                .withRenderMode(SimulationServerConfig.RenderMode.SERVER);


        DummySimulationExecutionConfigTemplate dummy = new DummySimulationExecutionConfigTemplate();
        SimulationExecutionConfig  simConfig = dummy.createSimulationExecutionConfig(cfg.getAssetFolders(),"Faculty_1floor");

        //59.26255f, 0.41312274f, 19.185688f
        Future<String> launchFuture = Future.future();
        SimulationServerLauncher.launch(this.vertx, cfg, launchFuture.completer());
        launchFuture.mapEmpty()
                .compose( _Void -> CreateSimWithJson(simConfig.toJson()))
                .setHandler(ar -> {
                    if (ar.failed())
                    {
                        log.error("Failed to create simulations", ar.cause());
                        System.exit(-1);

                    } else
                    {
                        Long simID = ar.result();
                        CreateHumans(simID,simConfig);
                    }
                });
    }



    private void ConfigureHuman(HumanAgentService agentService,JsonPoint point,AgentDescription humanDescription)
    {
        agentService.createHuman(point, ar -> {
            if (ar.failed())
            {
                log.error("Human creation failed", ar.cause());
            } else
            {
                try {
                    long humanId = ar.result();
                    String behaviorUsed = humanDescription.getBehaviorUsed();
                }
                catch(Exception e)
                {
                    log.error("Error to execute command create "+e.getMessage());
                }
            }
        });
    }
}


