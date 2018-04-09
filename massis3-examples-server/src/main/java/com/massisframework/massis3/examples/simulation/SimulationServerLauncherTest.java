package com.massisframework.massis3.examples.simulation;

import java.io.IOException;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.massisframework.massis3.simulation.server.SimulationServerLauncher;

public class SimulationServerLauncherTest {

    private static final Logger log = LoggerFactory.getLogger(SimulationServerLauncher.class);

    public static void main(String[] args) throws IOException {
//		if (args.length == 0)
//		{
//			log.error("Configuration file is required, load default configuration");
//			System.exit(-1);
//		}
        String path = "configurations/local-config.json";
        SimulationServerLauncher.launch(Paths.get(path));
    }
}
