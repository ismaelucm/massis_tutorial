package com.massisframework.massis3.examples.simulation.SimulationDescriptions.SimulationDescriptionHelper;

import com.massisframework.massis3.LUA.LuaParser;
import com.massisframework.massis3.core.config.*;
import com.massisframework.massis3.core.config.AgentConfig;
import com.massisframework.massis3.examples.simulation.LancherDefs;
import com.massisframework.massis3.examples.simulation.LaunchServer;
import com.massisframework.massis3.examples.simulation.SimulationDescriptions.PreconfiguredSimulation;
import com.massisframework.massis3.examples.simulation.SimulationDescriptions.templates.SimulationByFileConfigTemplate;
import com.massisframework.massis3.simulation.server.SimulationServerLauncher;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class SimulationByFile extends PreconfiguredSimulation {

    private static final Logger log = LoggerFactory.getLogger(LaunchServer.class);

    public String getSimulationName()
    {
        return "Faculty_1floor";
    }

    public String getClassName()
    {
        return SimulationByFile.class.getName();
    }

    private static JsonObject _configuration;

    public static void setScenarioConfiguration(String jsonFile)
    {
        _configuration = new JsonObject(jsonFile);
    }

    public static String luaToJSON(String luaFile)
    {
        ScenarioConfig scenarioConfig = LuaParser.loadScenario(luaFile);
        String scenarioStr = scenarioConfig.toJson().toString();
        return scenarioStr;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception
    {
        io.vertx.core.json.JsonObject config = this.config();
        String ip = config.getString(LancherDefs.IP);
        int port = config.getInteger(LancherDefs.PORT).intValue();
        String assetPath  = config.getString(LancherDefs.ASSETS_PATH);

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

        SimulationExecutionConfig simConfig = createCustomSimExecConfig(cfg.getAssetFolders(),"Faculty_1floor");
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

    protected SimulationExecutionConfig createCustomSimExecConfig(List<String > assetsFolder, String scene)
    {
        SimulationByFileConfigTemplate template = new SimulationByFileConfigTemplate();
        SimulationExecutionConfig simExecConfig = template.createSimulationExecutionConfig(assetsFolder,scene);

        ScenarioConfig scenarioConfig = new ScenarioConfig(_configuration);

        simExecConfig.withScenarioConfig(scenarioConfig);
        return simExecConfig;
    }




}