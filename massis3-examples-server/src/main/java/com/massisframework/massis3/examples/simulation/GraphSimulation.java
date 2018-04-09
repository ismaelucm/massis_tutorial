package com.massisframework.massis3.examples.simulation;

import com.massisframework.massis3.simulator.Components.HelpperClasses.SimulationResult;
import com.massisframework.massis3.simulator.Configuration.ArquetypeSetDescription;
import com.massisframework.massis3.simulator.Configuration.SimulationDescription;
import com.massisframework.massis3.simulator.Core.Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class GraphSimulation
{

    public static void main(String[] args) throws Exception {
        String currentPath = MainUtils.getCurrentPath();

        String arquetype    = currentPath+"/massis3-examples/src/main/resources/ArquetypeSimulationConfigExample.xml";
        System.out.println("arquetype=>"+arquetype);
        String scene        = currentPath+"/massis3-examples/src/main/resources/SceneExample.xml";
        System.out.println("scene=>"+scene);

        try {
            InputStream arquetypeStream = new FileInputStream(arquetype);
            InputStream sceneStream = new FileInputStream(scene);

            ArquetypeSetDescription arquetypeSetDescription = new ArquetypeSetDescription(arquetypeStream);

            SimulationDescription simDescription = new SimulationDescription(sceneStream);

            Simulation simulation = new Simulation();
            simulation.addArquetype(arquetypeSetDescription);
            simulation.configure(simDescription);

            simulation.Run();

            //simulation.end();


            SimulationResult simulationResult = simulation.getResultType();
            Map<String, Integer> resMap = simulationResult.getAllResults();
            for(Map.Entry<String, Integer> entry : resMap.entrySet())
            {
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
            //int numDetected =  simulationResult.getSensorResult("Sensor1");

            //System.out.println("Number of pedestrian detected: "+numDetected);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
