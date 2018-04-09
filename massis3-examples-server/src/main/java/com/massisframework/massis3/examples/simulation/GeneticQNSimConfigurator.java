package com.massisframework.massis3.examples.simulation;

import com.massisframework.massis3.simulator.Core.Simulation;

public class GeneticQNSimConfigurator {

    public static void main(String[] args)
    {
        String currentPath = MainUtils.getCurrentPath();
        if(args.length > 0)
        {
            //configuración por consola
        }

        Simulation simulation = GeneticBehavior.CreateSimulation();
    }
}
