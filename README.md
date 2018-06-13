# massis3-4
## Compile
It is assumed massis3 is installed.  If not
	
	git clone git@bitbucket.org:marlonca/massis3-4.git

And then, compiling for development:

	mvn compile install

Warning: When you do git clone from the repository, you must add the file
profiles/dev/config.properties to local git ignored files and modify
the local values of host, port and assets path.

Example content

	host_ip=127.0.0.1
	host_port=8080
	host_path=**MYPATHTOMASSIS**/massis3-assets/

To do this run the following command in git:

	git update-index --assume-unchanged profiles/dev/config.properties

If needed, because perhaps you deleted the file, to revert the ignored file type the following command:

	git update-index --no-assume-unchanged	

To run demos
-------------
To know which simulations are available

	sh LaunchServer.sh -l

There exist two simulations predefined:

* Faculty_1floor. The population is defined in class DummySimulationExecutionConfigTemplate, and consists of five big groups of 25 persons, starting all of them at the same location
* EnterToClassFaculty_1floor is nowadays broken.

To launch either of these:

	sh LaunchServer.sh -s Faculty_1floor
	sh LaunchServer.sh -s EnterToClassFaculty_1floor

New simulations can be programmed and instructions will be given in othe sections of this README.
It is possible too 

	sh LaunchServer.sh -f <file_human_description_lua>

Where <file_human_description_lua> is a file where the user can describe different human population profiles to include in the simulation. Exist the following examples of this file in the directory simulationExamples:

* SimpleExample01.lua : A simple simulation where we create one pedestrian outside the faculty going to the first classroom. Its walking speed is randomly between 1 - 5 and it is using the behavior FollowingPath

To run this example

	sh LaunchServer.sh -f SimpleExample01.lua
	
* SimpleExample02.lua: This simulation creates two groups of pedestrian, one of then in the main gate and another in the back gate. Both walking with different speeds between 1-5 and with the destination at the first classroom.

 To run this example:

	sh LaunchServer.sh -f SimpleExample02.lua

This is one of the multicamera setups. You can see other cameras at:

	http://localhost:8080/api/simulations/0/environment/cameraweb

* EntranceToclass.lua: This simulation creates ten groups of pedestrian, five of them in the main gate and another five in the back gate. All groups walking with different speeds between 1-5 and with the destination at the different classrooms.
All they using the behavior FollowingPath

 To run this example:

	sh LaunchServer.sh -f EntranceToclass.lua

This is one of the multicamera setups. You can see other cameras at:

	http://localhost:8080/api/simulations/0/environment/cameraweb

* EntranceToClassDifferentWaves.lua: The simulation has two pedestrian waves. In the first wave, the simulation creates twelve groups of pedestrian, six of them in the main gate and another six in the back gate. All groups walking with different speeds between 1-5 and with the destination at the different classrooms and the cafeteria. The second wave is the same of the first wave, but start 50 seconds after. All they using the behavior FollowingPath

 To run this example:

	sh LaunchServer.sh -f EntranceToClassDifferentWaves.lua

* Evacuation.lua: The simulation simulates an evacuation of the five classrooms. The path to follow has been determined in the simulation.
	* The populations Aula1, Aula2, Aula3 are evacuated through the main door
	* The populations Aula4, Aula5, Aula3 are evacuated through the back door
	* the population Cafeteria is evacuated using the shortes path to through the main door
	* All they using the behavior FollowingPath

 To run this example:

	sh LaunchServer.sh -f EvacuationWithVariableSpeed.lua

* SecurityThief.lua: The simulation has three profiles: Victims, Thief and Security. 
	* The security patrolling around the hall.
	* The victims walking around the elevator. Use the behavior FollowingPath.
	* the thief appears at 20 seconds and select a victim randomly. Use the behavior Thief.
	* the security agent sees the thief and this is suspicious enough (in this case it is) he chase him. Use the behavior Security.

 To run this example:

	sh LaunchServer.sh -f SecurityThief.lua


To show the help you must run the following command:

	sh LaunchServer.sh -h

To developing new demos or modify the existing.
-----------------------------------------------



The classes where these simulations have been implemented are:

* Faculty_1floor => ./src/main/java/com/massisframework/massis3/examples/simulation/SimulationDescriptions/SimulationFaculty_1floor.java
* EnterToClassFaculty_1floor => ./src/main/java/com/massisframework/massis3/examples/simulation/SimulationDescriptions/EnterToClassFaculty_1floor.java


If you want to create a new simulation predefined, you must create a class that extends of **PreconfiguredSimulation**. Existe a template to create simulations. This template was accessible implementing the interfaces **SimulationExecutionConfigTemplate**.

Debugging the navmesh
---------------------


To show the navigation navmesh you must set true in the class NavmeshVisualizerSystem the constant showNavmesh.


Remote Access to the simulations
--------------------------------

The system allows getting information using web services in order to consult and manipulate the simulations. For example if you can consult the position of the human hagent you can do a get query using curl or another http client like this:

	get http://[server]:[port]/api/simulations/[simID]/human-agent/allHumanInfo/

where: 

* server: is the ip where the simulation server is running.
* port: is the port where the simulation server is running.
* simID: is the simulation ID. If only are running one simulation, the simulation id is 0.



To consult human positions only

	
	get http://[server]:[port]/api/simulations/[simID]/human-agent/positions/


To consult the physical rooms


	get http://[server]:[port]/api/simulations/[simID]/environment/rooms/

To consult the behavior and anction currently running by the all humans


	get http://[server]:[port]/api/simulations/[simID]/human-agent/behaviors/
 

To consult the behavior and anction currently running by an single human


	get http://[server]:[port]/api/simulations/[simID]/human-agent/behavior/[entityID]

where: 

* entityId: is the internal id of the human.


To consult the information of an single human


	get http://[server]:[port]/api/simulations/[simID]/human-agent/humanInfo/[entityID]


To get the behavior description of the human you can run the following command:


	get http://localhost:8080/api/simulations/0/human-agent/behavior-description/[behaviorName]/

where: 

* behaviorName: is the name of the behavior


To get the camera ids available in the simulation

	http://localhost:8080/api/simulations/0/environment/cameraIds

URL to access the simulation via a dashboard. The user is presented a div with all available cameras

	http://localhost:8080/api/simulations/0/environment/cameraweb

Streaming the video of the simulation in MJPEG of a particular camera

	http://localhost:8080/api/simulations/0/environment/camera/0/video

LUA syntax
-------------

A lua file has the following syntax

	 CameraConfig
	 [VideoConfig]
	 Agentdescription
	 Commands
 
Camera config can be single or multiple. When single, the camera config has three attributes: its location, the rotation of the camera and where it is looking at.
To facilitate creating new cameras correctly oriented, you can use the mouse and keyboard (q+z+a+s+w+d) to operate the camera, and then press "c". The console will display current location,rotation, and lookat values that you can reuse. Read the tutorial for more details.

	CameraConfig= {
		location = { 61.0, 100.0, 40.0 },
		rotation = { 90.0, 0.0, 0.0 },
		lookAt = { 61.0, 0.0, 40.0 }
		}

For multiple cameras, add an additional curly braces. The first declared camera is the main camera.

	CameraConfig=  {{
				location = { 61.0, 100.0, 40.0 },
				rotation = { 90.0, 0.0, 0.0 },
				lookAt = { 61.0, 0.0, 40.0 }
			}, 
			{
				location = { 61.0, 100.0, 40.0 },
				rotation = { 90.0, 0.0, 0.0 },
				lookAt = { 61.0, 0.0, 40.0 }
			}
			}

The created cameras should be accesible from :

	http://localhost:8080/api/simulations/0/environment/cameraweb

The VideoConfig syntax is

	VideoConfig={
		filename="output_video_filename_path.flv",
		cameraId="ID of the camera (0 if don't know)",
		duration="seconds of the simulation to record",
	},

The file with the video is coded as a flv container with H264 codec. The internal format is FLV to get wider acceptance. Due to some issues, the recorded video is still not of the same duration, but an accelerated version of the same simulation duration. It will use as input the main camera.

Trouble shooting
--------------------

IF you get this message

Failed to execute goal on project jsonschema2pojo-annotators: Could not resolve dependencies for project com.massisframework.massis3:jsonschema2pojo-annotators:jar:1.0-SNAPSHOT: Could not find artifact javafx-packager:javafx-packager:jar:1.8.0_20 at specified path /usr/lib/jvm/java-8-openjdk-amd64/jre/../lib/ant-javafx.jar -> [Help 1]

If you are using OpenJDK, then try installing either Java SDK from sun or install the openjfx. 

In ubuntu/debian

	sudo apt-get install openjfx

If you get this messsage

	[]java.lang.RuntimeException: java.lang.RuntimeException: java.nio.file.NoSuchFileException: SOME_WHATEVER_PATH/massis3-assets/Scenes
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)

Try checking massis3-example/profiles/dev/config.properties and set the host_path to the right assets place 


IF the launch of the program gets stuck at something like

	WARNING: Cannot find loader com.jme3.audio.plugins.OGGLoader
	[[MultiApp#1](Graphics test)] INFO SimulationServerLauncher - Test application launched and succeed. Shutting it down
	Jan 24, 2018 8:18:33 PM com.jme3.asset.AssetConfig loadText
	WARNING: Cannot find loader com.jme3.audio.plugins.OGGLoader
	Jan 24, 2018 8:18:33 PM com.jme3.asset.AssetConfig loadText
	WARNING: Cannot find loader com.jme3.audio.plugins.OGGLoader
	Jan 24, 2018 8:18:33 PM com.jme3.asset.AssetConfig loadText
	WARNING: Cannot find loader com.jme3.audio.plugins.OGGLoader
	Jan 24, 2018 8:18:33 PM com.jme3.asset.AssetConfig loadText
	WARNING: Cannot find loader com.jme3.audio.plugins.OGGLoader
	Jan 24, 2018 8:18:33 PM com.jme3.asset.AssetConfig loadText
	WARNING: Cannot find loader com.jme3.audio.plugins.OGGLoader
	Jan 24, 2018 8:18:33 PM com.jme3.asset.AssetConfig loadText
	WARNING: Cannot find loader com.jme3.audio.plugins.OGGLoader
	[ForkJoinPool.commonPool-worker-1] INFO MassisSceneCompiler - Compiling scene /home/mosi/.massis/assets/BigSimpleSpace.sh3d
	[ForkJoinPool.commonPool-worker-1] INFO MassisSceneCompiler - Exporting XML Home
	libEGL warning: DRI2: failed to authenticate

It maybe the case you have problems with your graphics card. In Linux, this happens when you try to launch the application within a ssh session with X, e.g. "ssh -X ...". You need to launch the application within the same X session you have logged in when starting up the computer.

It takes too long to launch, and it shows weird messages such as

	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[0] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[1] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[0] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[0] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[1] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[0] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[0] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[1] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[0] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[1] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.loader.GroupedObjLoader readFace
	WARNING: Edge or polygon detected in OBJ. Ignored.
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.loader.GroupedObjLoader createGeometry
	WARNING: OBJ mesh chaiseJaune-geom-1 doesnt contain normals! It might not display correctly
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[0] (Geometry)
	Jan 24, 2018 8:21:20 PM com.massisframework.massis3.commons.spatials.FastLodGenerator lambda$bakeAllLods$0
	INFO: Baking geometry batch[1] (Geometry)
	Jan 24, 2018 8:21:20 PM com.jme3.util.TangentBinormalGenerator processTriangle
	WARNING: Colinear uv coordinates for triangle [8, 9, 11]; tex0 = [0, 0], tex1 = [0, 1], tex2 = [0, 0]
	Jan 24, 2018 8:21:20 PM com.jme3.util.TangentBinormalGenerator processTriangle
	WARNING: Colinear uv coordinates for triangle [9, 10, 11]; tex0 = [0, 1], tex1 = [0, 1], tex2 = [0, 0]
	Jan 24, 2018 8:21:20 PM com.jme3.util.TangentBinormalGenerator processTriangle
	WARNING: Colinear uv coordinates for triangle [12, 13, 15]; tex0 = [1, 1], tex1 = [1, 1], tex2 = [1, 0]
	Jan 24, 2018 8:21:20 PM com.jme3.util.TangentBinormalGenerator processTriangle
	WARNING: Colinear uv coordinates for triangle [13, 14, 15]; tex0 = [1, 1], tex1 = [1, 0], tex2 = [1, 0]
	Jan 24, 2018 8:21:20 PM com.jme3.util.TangentBinormalGenerator processTriangle
	WARNING: Colinear uv coordinates for triangle [16, 17, 19]; tex0 = [0, 1], tex1 = [1, 1], tex2 = [0, 1]
	Jan 24, 2018 8:21:20 PM com.jme3.util.TangentBinormalGenerator processTriangle
	WARNING: Colinear uv coordinates for triangle [17, 18, 19]; tex0 = [1, 1], tex1 = [1, 1], tex2 = [0, 1]

The program uses originally sweet home 3D files that need to be converted to massis format (JMonkey with the appropriate additional files). This requires a compilation of the assets that may take from 2 to 5 minutes in a core i7. You can check $HOME/.massis/assets and watch as the new files are created. This happens only once,unless you modify one of the sweet home 3D files.

