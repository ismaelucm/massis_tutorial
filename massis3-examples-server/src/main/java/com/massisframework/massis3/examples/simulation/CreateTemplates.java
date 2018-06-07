package com.massisframework.massis3.examples.simulation;

import com.massisframework.massis3.FileHelpper;
import com.massisframework.massis3.core.config.AgentConfig;
import com.massisframework.massis3.core.config.SimulationExecutionConfig;
import com.massisframework.massis3.examples.simulation.SimulationDescriptions.templates.DummySimulationExecutionConfigTemplate;

import java.util.ArrayList;

public class CreateTemplates {

    public static void main(String[] args) {
        String simulationConfigFile = "SimulationConfig.json";
        String humanConfigFile = "HumanConfig.json";
        DummySimulationExecutionConfigTemplate dummy = new DummySimulationExecutionConfigTemplate();
        SimulationExecutionConfig config = dummy.createSimulationExecutionConfig(new ArrayList<>(), "Faculty_1floor");
        AgentConfig humanConfig = dummy.CreateAgentConfig();
        try {
            String simulationConfigData = config.toJson().toString();
            String humanConfigData = humanConfig.toJson().toString();
            FileHelpper.easyWriteFile(simulationConfigFile, simulationConfigData);
            FileHelpper.easyWriteFile(humanConfigFile, humanConfigData);
        } catch (Exception e) {
            System.out.println("Exception when creating files");
        }
    }
}
