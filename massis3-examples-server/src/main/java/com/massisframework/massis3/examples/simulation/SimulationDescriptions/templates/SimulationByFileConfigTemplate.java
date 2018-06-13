package com.massisframework.massis3.examples.simulation.SimulationDescriptions.templates;

import com.massisframework.massis3.core.config.CameraConfig;
import com.massisframework.massis3.core.config.AgentConfig;
import com.massisframework.massis3.core.config.SimulationExecutionConfig;
import com.massisframework.massis3.core.config.WorldConfig;

import java.util.List;

public class SimulationByFileConfigTemplate extends DummySimulationExecutionConfigTemplate {

    @Override
    public SimulationExecutionConfig createSimulationExecutionConfig(List<String> assetsFolder, String scene) {
        //CameraConfig camConfig = CreateCameraConfig();
        WorldConfig wordConfig = CreateWorld();

        SimulationExecutionConfig simConfig = new SimulationExecutionConfig()
                .withWorldConfig(wordConfig)
                .withAssetFolders(assetsFolder)
                .withDebugEnabled(false)
                .withSceneFile(scene);

        return simConfig;
    }
}
