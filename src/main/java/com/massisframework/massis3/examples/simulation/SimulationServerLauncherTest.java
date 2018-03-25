package com.massisframework.massis3.examples.simulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.app.SimpleApplication;
import com.jme3.system.JmeContext.Type;
import com.massisframework.massis3.commons.app.server.ServerJMEApplication;
import com.massisframework.massis3.commons.app.server.impl.multi.MultiCameraApp;
import com.massisframework.massis3.commons.loader.LocalMassisSceneLoader;
import com.massisframework.massis3.configuration.Configuration;
import com.massisframework.massis3.core.config.HttpServerConfig;
import com.massisframework.massis3.core.config.SimulationServerConfig;
import com.massisframework.massis3.simulation.server.SimulationServerLauncher;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

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
