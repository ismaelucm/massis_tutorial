package com.massisframework.massis3.examples.simulation.SimulationDescriptions.templates;

import com.massisframework.massis3.core.config.*;
import com.massisframework.massis3.core.config.FSM.*;
import com.massisframework.massis3.services.dataobjects.JsonPoint;
import com.massisframework.massis3.services.dataobjects.JsonQuaternion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface SimulationExecutionConfigTemplate
{
    SimulationExecutionConfig createSimulationExecutionConfig(List<String > assetsFolder, String scene);
    WorldConfig CreateWorld();
    RectangleRegion CreateRegion(String name,float xMin, float xMax, float zMin, float zMax);
    AgentConfig CreateAgentConfig();
    CameraConfig CreateCameraConfig();

}
