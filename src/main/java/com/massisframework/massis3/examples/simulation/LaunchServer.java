package com.massisframework.massis3.examples.simulation;

import com.massisframework.massis3.CommandLineConfig;
import com.massisframework.massis3.CommandLineInfo;
import com.massisframework.massis3.FileHelpper;
import com.massisframework.massis3.configuration.Configuration;
import com.massisframework.massis3.core.BehaviorPrimitives.MassisBehaviorManager;
import com.massisframework.massis3.core.config.*;
import com.massisframework.massis3.examples.simulation.SimulationDescriptions.PreconfiguredSimulation;
import com.massisframework.massis3.LUA.LuaParser;
import com.massisframework.massis3.examples.simulation.SimulationDescriptions.SimulationDescriptionHelper.SimulationByFile;
import com.massisframework.massis3.services.eventbus.Massis3ServiceUtils;
import com.massisframework.massis3.services.eventbus.SimulationServerService;
import com.massisframework.massis3.simulation.server.SimulationServerLauncher;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LaunchServer
{
    public enum FIleType {XML,JSON,LUA}
    private static final Logger log = LoggerFactory.getLogger(LaunchServer.class);
    private static final String BEHAVIORS_PATH = "/CrowdBehaviors/";

    public static void main(String[] args) throws Exception
    {
        processBehavior(BEHAVIORS_PATH);
        System.out.println("");
        System.out.println("MASSIS LaunchServer start");
        System.out.println("=========================");
        System.out.println("");
        if(args.length == 0)
        {
            //configure deault args
            args = new String[2];
            args[0] = "-f";
            args[1] = "EvacuationWithVariableSpeed.lua";
            //args[1] = "SuspectBehaviorExample.lua";
            //args[0] = "-s";
            //args[1] = "Faculty_1floor";
        }

        CommandLineInfo[] clInfo = {
                new CommandLineInfo<String>(LancherDefs.HELP,"Help: Show the help",null),
                new CommandLineInfo<String>(LancherDefs.IP,"IP: The server IP", "<IP>", Configuration.instance().getHost()),
                new CommandLineInfo<Integer>(LancherDefs.PORT,"Port: The server port", "<PORT>", Configuration.instance().getPort()),
                new CommandLineInfo<Integer>(LancherDefs.FILE,"File: The json where is defined the behavior of the humans in the simulation", "<json file>"),
                new CommandLineInfo<String>(LancherDefs.SIMULATION,"Simulation: The predefined description of the simulation","<scene name>","Faculty_1floor"),
                new CommandLineInfo<String>(LancherDefs.SIMULATION_LIST,"List of predefined simulations availables",null)
        };
        CommandLineConfig config = new CommandLineConfig("Massis server, Version 3.4",clInfo,"-h");

        //LuaParser.test();

        if(config.parser(args,log)) {

            if(config.isAvailable(LancherDefs.SIMULATION_LIST))
                ShowSimulationsPredefined();
            else if(config.isAvailable(LancherDefs.SIMULATION))
                LaunchWithScene(config);
            else if(config.isAvailable(LancherDefs.FILE)) {
                LaunchWithFileScene(config, FIleType.LUA);
                //execMassisLuaCode
            }
            else
                Launch(config);
        }
        System.out.println("");
        System.out.println("MASSIS LaunchServer end");
        System.out.println("=======================");
        System.out.println("");

    }

    private static void processBehavior(String behaviorPath) throws IOException
    {
        List<String> allBehaviorsFile = FileHelpper.getResourceListAsStrings(behaviorPath);
        for (String bhFile : allBehaviorsFile)
        {
          //  String bhFile = FileHelpper.easyReadFile(f);
            BehaviorDef behaviorDef = LuaParser.loadBehavior(bhFile);
            if(behaviorDef.getBehaviorType() == BehaviorDef.BehaviorType.FSM) {
                MassisBehaviorManager.instance().addBehavior(behaviorDef);
            }
        }
    }

    public static void ShowSimulationsPredefined()
    {
        List<String> list = PreconfiguredSimulation.getList();
        System.out.println("List of predefined simulations:");
        for(String s : list)
        {
            System.out.println(s);
        }
    }

    public static void LaunchWithScene(CommandLineConfig config) throws Exception
    {
        PreconfiguredSimulation sim = PreconfiguredSimulation.getSim(config.<String>getInfo(LancherDefs.SIMULATION).getValue());
        LaunchScene(config,sim);
    }

    public static void LaunchScene(CommandLineConfig config,PreconfiguredSimulation sim) throws Exception {
        String ip = config.<String>getInfo(LancherDefs.IP).getValue();
        int port = config.<Integer>getInfo(LancherDefs.PORT).getValue().intValue();
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions();
        JsonObject configJson = new JsonObject();
        configJson.put(LancherDefs.IP,ip);
        configJson.put(LancherDefs.PORT,port);
        configJson.put(LancherDefs.ASSETS_PATH,Configuration.instance().getPath());

        options.setConfig(configJson);
        vertx.deployVerticle(sim.getClassName(),options
                , r -> {
                    if (r.failed()) {
                        r.cause().printStackTrace();
                        log.error("Error when launching verticle", r.cause());
                    } else {

                        if (log.isInfoEnabled()) {
                            log.info("Verticle launched successfully");
                        }

                    }
                });
    }


    public static void LaunchWithFileScene(CommandLineConfig config, FIleType filtType) throws Exception
    {
        String filePath = config.<String>getInfo(LancherDefs.FILE).getValue();
        String fileComplete = FileHelpper.easyReadFile(filePath);
        if(fileComplete != null) {

            if(filtType == FIleType.JSON)
            {
                LaunchWithJsonFile(fileComplete,config);
            }
            else if(filtType == FIleType.LUA)
            {
                LaunchWithLuaFile(fileComplete,config);
            }
        }
        else
        {
            log.error("File "+filePath+" not found ");
        }


    }

    protected static void LaunchWithLuaFile(String fileComplete,CommandLineConfig config) throws Exception
    {
        String jsonFormat = SimulationByFile.luaToJSON(fileComplete);
        LaunchWithJsonFile(jsonFormat,config);
    }

    protected static void LaunchWithJsonFile(String fileComplete,CommandLineConfig config) throws Exception
    {
        SimulationByFile.setScenarioConfiguration(fileComplete);
        SimulationByFile simByFile = new SimulationByFile();
        LaunchScene(config,simByFile);
    }



    public static void Launch(CommandLineConfig config) throws Exception
    {
        Vertx vertxServer = Vertx.vertx(); // vertexServer is an instance of vertx used to launch the web services.
        SimulationServerConfig cfg = new SimulationServerConfig()
                .withAssetFolders(Arrays.asList(
                        Configuration.instance().getPath()+"Scenes",
                        Configuration.instance().getPath()+"models",
                        Configuration.instance().getPath()+"animations"))
                .withHttpServerConfig(
                        new HttpServerConfig().withHost(config.<String>getInfo("-i").getValue()).withPort(config.<Integer>getInfo("-p").getValue().intValue()))
                .withAuthPropertiesFile("classpath:webauth.properties")
                .withInstances(1)
                .withRendererType(SimulationServerConfig.RendererType.LWJGL_OPEN_GL_3)
                .withRenderMode(SimulationServerConfig.RenderMode.DESKTOP);

        Future<String> launchFuture = Future.future();
        SimulationServerLauncher.launch(vertxServer, cfg, launchFuture.completer());
    }

    public static void LaunchHand() throws Exception
    {
        Vertx vertxServer = Vertx.vertx(); // vertexServer is an instance of vertx used to launch the web services.
        SimulationServerConfig cfg = new SimulationServerConfig()
                .withAssetFolders(Arrays.asList(
                        Configuration.instance().getPath()+"Scenes",
                        Configuration.instance().getPath()+"models",
                        Configuration.instance().getPath()+"animations"))
                .withHttpServerConfig(
                        new HttpServerConfig().withHost(Configuration.instance().getHost()).withPort(Configuration.instance().getPort()))
                .withAuthPropertiesFile("classpath:webauth.properties")
                .withInstances(1)
                .withRendererType(SimulationServerConfig.RendererType.LWJGL_OPEN_GL_3)
                .withRenderMode(SimulationServerConfig.RenderMode.DESKTOP);

        Future<String> launchFuture = Future.future();
        SimulationServerLauncher.launch(vertxServer, cfg, launchFuture.completer());

    }

    private static Future<Long> createSim(Vertx vertx, String sceneFile)
    {
        SimulationServerService proxy = Massis3ServiceUtils.createProxy(
                vertx,
                SimulationServerService.class,
                Massis3ServiceUtils.GLOBAL_SERVICE_GROUP);
        Future<Long> simCreateFuture = Future.future();
        proxy.create(sceneFile, simCreateFuture.completer());
        return simCreateFuture;
    }
}
