package com.massisframework.massis3.examples.simulation.SimulationDescriptions;


import com.massisframework.massis3.core.components.RewriteParameter;
import com.massisframework.massis3.core.config.*;
import com.massisframework.massis3.examples.simulation.LancherDefs;
import com.massisframework.massis3.examples.simulation.SimulationDescriptions.templates.SimulationExecutionConfigTemplate;
import com.massisframework.massis3.services.dataobjects.JsonPoint;
import com.massisframework.massis3.services.dataobjects.JsonQuaternion;
import com.massisframework.massis3.simulation.server.SimulationServerLauncher;
import io.vertx.core.Future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnterToClassFaculty_1floor extends PreconfiguredSimulation implements SimulationExecutionConfigTemplate {


    public String getSimulationName() {
        return "EnterToClassFaculty_1floor";
    }

    public String getClassName() {
        return EnterToClassFaculty_1floor.class.getName();
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        io.vertx.core.json.JsonObject config = this.config();
        String ip = config.getString(LancherDefs.IP);
        int port = config.getInteger(LancherDefs.PORT).intValue();
        String assetPath = config.getString(LancherDefs.ASSETS_PATH);
        // 1. Deploy simulation server verticle
        // cfg=JsonObject.mapFrom(this.config());
        SimulationServerConfig cfg = new SimulationServerConfig()
                .withAssetFolders(Arrays.asList(
                        assetPath + "Scenes",
                        assetPath + "models",
                        assetPath + "animations"))
                .withHttpServerConfig(
                        new HttpServerConfig().withHost(ip).withPort(port))
                .withAuthPropertiesFile("classpath:webauth.properties")
                .withInstances(1)
                .withRendererType(SimulationServerConfig.RendererType.LWJGL_OPEN_GL_3)
                .withRenderMode(SimulationServerConfig.RenderMode.SERVER);

        CameraConfig camConfig = new CameraConfig();
        camConfig.setLocation(new JsonPoint(33.0f, 165.0f, 54.0f));
        //.withLocation(new JsonPoint(33.0f, 165.0f, 54.0f))
        camConfig.withRotation(new JsonPoint(90.0f, 0.0f, 0.0f))
                .withLookAt(new JsonPoint(0.0f, 0.0f, 0.0f));

        AgentDescription population01 = new AgentDescription()
                .withName("population01")
                .withModel("")
                .withBehaviorUsed("FollowingPath");

        AgentDescription population02 = new AgentDescription()
                .withName("population02")
                .withModel("")
                .withBehaviorUsed("FollowingPath");


        SimulationExecutionConfig simConfig = createSimulationExecutionConfig(cfg.getAssetFolders(), "Faculty_1floor");

        //59.26255f, 0.41312274f, 19.185688f
        Future<String> launchFuture = Future.future();
        SimulationServerLauncher.launch(this.vertx, cfg, launchFuture.completer());
        launchFuture.mapEmpty()
                .compose(_Void -> CreateSimWithJson(simConfig.toJson()))
                .setHandler(ar -> {
                    if (ar.failed()) {
                        log.error("Failed to create simulations", ar.cause());
                        System.exit(-1);

                    } else {
                        Long simID = ar.result();
                        CreateHumans(simID, simConfig);
                    }
                });
    }

    @Override
    public SimulationExecutionConfig createSimulationExecutionConfig(List<String> assetsFolder, String scene) {
        CameraConfig camConfig = CreateCameraConfig();
        WorldConfig wordConfig = CreateWorld();
        AgentConfig agentConfig = CreateAgentConfig();


        ScenarioConfig scenarioConfig = new ScenarioConfig()
                .withCameraConfig(camConfig)
                .withAgentConfig(agentConfig)
                .withCommands(Arrays.asList(
                        //"create population01 20 (94.19, 2.3800337f, 71.662235f)",
                        "create population02 1 MainGate"
                ));

        SimulationExecutionConfig simConfig = new SimulationExecutionConfig()
                .withWorldConfig(wordConfig)
                .withAssetFolders(assetsFolder)
                .withDebugEnabled(false)
                .withSceneFile(scene)
                .withScenarioConfig(scenarioConfig);
        //.withHumanConfig(humanConfig);


        return simConfig;
    }

    @Override
    public CameraConfig CreateCameraConfig() {
        CameraConfig camConfig = new CameraConfig()
                .withLocation(new JsonPoint(33.0f, 165.0f, 54.0f))
                .withRotation(new JsonPoint(90.0f, 0.0f, 0.0f))
                .withLookAt(new JsonPoint(0.0f, 0.0f, 0.0f));
        return camConfig;
    }

    @Override
    public AgentConfig CreateAgentConfig() {

        List<RewriteParameter> rewriteParametersPopulation01 = new ArrayList<>();
        RewriteParameter rewriteParam = new RewriteParameter();
        rewriteParam.setName("Path");
        rewriteParam.setValue("MainGate");
        rewriteParametersPopulation01.add(rewriteParam);

        AgentDescription population01 = new AgentDescription()
                .withName("population01")
                .withModel("")
                .withRewriteParameter(rewriteParametersPopulation01)
                .withBehaviorUsed("FollowingPath");

        List<RewriteParameter> rewriteParametersPopulation02 = new ArrayList<>();
        RewriteParameter rewriteParam2 = new RewriteParameter();
        rewriteParam2.setName("Path");
        rewriteParam2.setValue("MainGate");
        rewriteParametersPopulation02.add(rewriteParam2);

        AgentDescription population02 = new AgentDescription()
                .withName("population02")
                .withModel("")
                .withRewriteParameter(rewriteParametersPopulation02)
                .withBehaviorUsed("FollowingPath");

        AgentConfig agentConfig = new AgentConfig()
                .withAgentDescription(Arrays.asList(
                        population01,
                        population02
                ));

        return agentConfig;
    }

    public WorldConfig CreateWorld() {
        List<RectangleRegion> rRegionList = new ArrayList<>();
        rRegionList.add(CreateRegion("MainGate", 87f, 98f, 65f, 78f));

        rRegionList.add(CreateRegion("MainHall.Entrance", 87f, 97f, 51f, 63f));
        rRegionList.add(CreateRegion("MainHall.Library", 87f, 98f, 41f, 51f));

        rRegionList.add(CreateRegion("LobbyEventRoom.Hall", 87f, 98f, 37f, 41f));
        rRegionList.add(CreateRegion("LobbyEventRoom.EventRoom", 98f, 104f, 37f, 41f));
        rRegionList.add(CreateRegion("LobbyEventRoom.Final", 105f, 117f, 37f, 41f));

        rRegionList.add(CreateRegion("Library", 93f, 118f, 19f, 37f));
        rRegionList.add(CreateRegion("EventRoom", 99f, 118f, 44f, 66f));
        rRegionList.add(CreateRegion("LobbyCafeteria", 73f, 87f, 51f, 56f));
        rRegionList.add(CreateRegion("Council", 77f, 87f, 57f, 68f));
        rRegionList.add(CreateRegion("Council2", 77f, 87f, 41f, 51f));
        rRegionList.add(CreateRegion("ElevatorsLobby", 73f, 77f, 42f, 51f));

        rRegionList.add(CreateRegion("ClassesLobby.Class1", 74f, 87f, 37f, 42f));
        rRegionList.add(CreateRegion("ClassesLobby.Class2", 61f, 74f, 37f, 42f));
        rRegionList.add(CreateRegion("ClassesLobby.Class3", 49f, 61f, 37f, 42f));
        rRegionList.add(CreateRegion("ClassesLobby.Class4", 36f, 49f, 37f, 42f));
        rRegionList.add(CreateRegion("ClassesLobby.Class5", 23f, 36f, 37f, 42f));

        rRegionList.add(CreateRegion("CafeteriaHall", 63f, 73f, 51f, 55f));
        rRegionList.add(CreateRegion("ProfessorsCafeteria", 61f, 73f, 55f, 69f));
        rRegionList.add(CreateRegion("StudentsCafeteria", 22f, 61f, 48f, 68f));
        rRegionList.add(CreateRegion("HallBackGate.Cafeteria", 17f, 23f, 48f, 55f));
        rRegionList.add(CreateRegion("HallBackGate.Lobby", 17f, 23f, 36f, 48f));

        rRegionList.add(CreateRegion("BackGate", 10f, 15, 42f, 48f));

        rRegionList.add(CreateRegion("Class1", 74f, 87f, 18f, 37f));
        rRegionList.add(CreateRegion("Class2", 61f, 74f, 18f, 37f));
        rRegionList.add(CreateRegion("Class3", 49f, 61f, 18f, 37f));
        rRegionList.add(CreateRegion("Class4", 36f, 49f, 18f, 37f));
        rRegionList.add(CreateRegion("Class5", 23f, 36f, 18f, 37f));


        WorldConfig wordConfig = new WorldConfig()
                .withLimits(CreateRegion("Faculty_1floor", 11f, 124f, 4f, 89f))
                .withWorld(rRegionList);
        return wordConfig;
    }

    public RectangleRegion CreateRegion(String name, float xMin, float xMax, float zMin, float zMax) {
        return new RectangleRegion().withName(name)
                .withXMin(xMin)
                .withXMax(xMax)
                .withYMin(0f)
                .withYMax(3f)
                .withZMin(zMin)
                .withZMax(zMax);
    }

}
