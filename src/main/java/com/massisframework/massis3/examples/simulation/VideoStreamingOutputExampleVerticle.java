package com.massisframework.massis3.examples.simulation;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.massisframework.massis3.configuration.Configuration;
import com.massisframework.massis3.core.config.HttpServerConfig;
import com.massisframework.massis3.core.config.SimulationServerConfig;
import com.massisframework.massis3.core.config.SimulationServerConfig.RenderMode;
import com.massisframework.massis3.core.config.SimulationServerConfig.RendererType;
import com.massisframework.massis3.services.eventbus.Massis3ServiceUtils;
import com.massisframework.massis3.services.eventbus.SimulationServerService;
import com.massisframework.massis3.services.eventbus.sim.EnvironmentService;
import com.massisframework.massis3.simulation.server.SimulationServerLauncher;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class VideoStreamingOutputExampleVerticle extends AbstractVerticle {

	private static final Logger log = LoggerFactory
			.getLogger(VideoStreamingOutputExampleVerticle.class);
	static
	{
		Massis3ServiceUtils.configureVertxJSONMapper();
	}

//	private final String ASSETS_PATH = "/home/massis/massis3-assets/";
//	private final String SERVER_IP = "127.0.0.1";
//	private final int SERVER_PORT = 8082;

	@Override
	public void start(Future<Void> startFuture) throws Exception
	{
		// 1. Deploy simulation server verticle
		// cfg=JsonObject.mapFrom(this.config());
		SimulationServerConfig cfg = new SimulationServerConfig()
				.withAssetFolders(Arrays.asList(
						Configuration.instance().getPath()+"Scenes",
						Configuration.instance().getPath()+"models",
						Configuration.instance().getPath()+"animations"))
				.withHttpServerConfig(
						new HttpServerConfig().withHost(Configuration.instance().getHost()).withPort(Configuration.instance().getPort()))
				.withAuthPropertiesFile("classpath:webauth.properties")
				.withInstances(1)
				.withRendererType(RendererType.LWJGL_OPEN_GL_3)
				.withRenderMode(RenderMode.SERVER);
		SimulationServerLauncher.launch(this.vertx, cfg, ar -> {
			if (ar.failed())
			{
				startFuture.fail(ar.cause());
			} else
			{
				// We have deployed the simulation verticle. Let's create a
				// simulation
				createSim("Faculty_1floor")
						.compose(simId -> createCamera(simId))
						.map((Void) null)
						.setHandler(startFuture.completer());
				// Access via web to the MPEG Stream
			}
		});
	}

	private Future<String> createCamera(Long simId)
	{
		Future<String> cameraIdFut = Future.future();
		EnvironmentService es = Massis3ServiceUtils.createProxy(vertx, EnvironmentService.class,
				simId);
		es.addCamera(cameraIdFut.completer());
		return cameraIdFut;

	}

	private Future<Long> createSim(String sceneFile)
	{
		SimulationServerService proxy = Massis3ServiceUtils.createProxy(
				vertx,
				SimulationServerService.class,
				Massis3ServiceUtils.GLOBAL_SERVICE_GROUP);
		Future<Long> simCreateFuture = Future.future();
		proxy.create(sceneFile, simCreateFuture.completer());
		return simCreateFuture;
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception
	{
		super.stop(stopFuture);
	}

	public static void main(String[] args) throws IOException
	{
		Vertx vertx = Vertx.vertx();

		// String cfgPath = "configurations/rpax-local-config.json";
		// JsonObject config = new JsonObject(new
		// String(Files.readAllBytes(Paths.get(cfgPath))));

		vertx.deployVerticle(VideoStreamingOutputExampleVerticle.class.getName(),
				new DeploymentOptions() /* .setConfig(config) */ , r -> {
					if (r.failed())
					{
						r.cause().printStackTrace();
						log.error("Error when launching verticle", r.cause());
					} else
					{

						if (log.isInfoEnabled())
						{
							log.info("Verticle launched successfully");
						}

					}
				});

	}

}
