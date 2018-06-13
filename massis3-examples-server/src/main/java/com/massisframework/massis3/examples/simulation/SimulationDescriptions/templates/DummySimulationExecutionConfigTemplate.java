package com.massisframework.massis3.examples.simulation.SimulationDescriptions.templates;

import com.massisframework.massis3.core.components.RewriteParameter;
import com.massisframework.massis3.core.config.*;
import com.massisframework.massis3.services.dataobjects.JsonPoint;
import com.massisframework.massis3.services.dataobjects.JsonQuaternion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummySimulationExecutionConfigTemplate implements SimulationExecutionConfigTemplate {
    public SimulationExecutionConfig createSimulationExecutionConfig(List<String> assetsFolder, String scene) {
        CameraConfig camConfig = CreateCameraConfig();
        WorldConfig wordConfig = CreateWorld();
        AgentConfig agentConfig = CreateAgentConfig();

        ScenarioConfig scenarioConfig = new ScenarioConfig()
                .withCameraConfig(camConfig)
                .withAgentConfig(agentConfig)
                .withCommands(Arrays.asList(
                        "create populationClass1 25 MainGate",
                        "create populationClass2 25 MainGate",
                        "create populationClass3 25 MainGate",
                        "create populationClass4 25 MainGate",
                        "create populationClass5 25 MainGate"
                ));

        SimulationExecutionConfig simConfig = new SimulationExecutionConfig()
                .withWorldConfig(wordConfig)
                .withAssetFolders(assetsFolder)
                .withDebugEnabled(false)
                .withSceneFile(scene)
                .withScenarioConfig(scenarioConfig);

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

    public AgentConfig CreateAgentConfig() {
        List<RewriteParameter> rewritePopulationClass1 = new ArrayList<>();
        RewriteParameter param1 = new RewriteParameter();
        param1.setName("Path");
        param1.setValue("MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,Class1");
        rewritePopulationClass1.add(param1);

        AgentDescription populationClass1 = new AgentDescription()
                .withName("populationClass1")
                .withModel("")
                .withRewriteParameter(rewritePopulationClass1)
                .withBehaviorUsed("FollowingPath");

        /*********************************************************************/
        List<RewriteParameter> rewritePopulationClass2 = new ArrayList<>();
        RewriteParameter param2 = new RewriteParameter();
        param2.setName("Path");
        param2.setValue("MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,Class2");
        rewritePopulationClass2.add(param2);

        AgentDescription populationClass2 = new AgentDescription()
                .withName("populationClass2")
                .withModel("")
                .withRewriteParameter(rewritePopulationClass2)
                .withBehaviorUsed("FollowingPath");


        /*********************************************************************/
        List<RewriteParameter> rewritePopulationClass3 = new ArrayList<>();
        RewriteParameter param3 = new RewriteParameter();
        param3.setName("Path");
        param3.setValue("MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,Class3");
        rewritePopulationClass3.add(param3);

        AgentDescription populationClass3 = new AgentDescription()
                .withName("populationClass3")
                .withModel("")
                .withRewriteParameter(rewritePopulationClass3)
                .withBehaviorUsed("FollowingPath");

        /*********************************************************************/
        List<RewriteParameter> rewritePopulationClass4 = new ArrayList<>();
        RewriteParameter param4 = new RewriteParameter();
        param4.setName("Path");
        param4.setValue("MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,ClassesLobby.Class4,Class4");
        rewritePopulationClass4.add(param4);


        AgentDescription populationClass4 = new AgentDescription()
                .withName("populationClass4")
                .withModel("")
                .withRewriteParameter(rewritePopulationClass4)
                .withBehaviorUsed("FollowingPath");

        /*********************************************************************/
        List<RewriteParameter> rewritePopulationClass5 = new ArrayList<>();
        RewriteParameter param5 = new RewriteParameter();
        param5.setName("Path");
        param5.setValue("MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,ClassesLobby.Class4,Class4");
        rewritePopulationClass5.add(param5);

        AgentDescription populationClass5 = new AgentDescription()
                .withName("populationClass5")
                .withModel("")
                .withRewriteParameter(rewritePopulationClass5)
                .withBehaviorUsed("FollowingPath");

        AgentConfig humanConfig = new AgentConfig()
                .withAgentDescription(Arrays.asList(
                        populationClass1,
                        populationClass2,
                        populationClass3,
                        populationClass4,
                        populationClass5));


        return humanConfig;
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
