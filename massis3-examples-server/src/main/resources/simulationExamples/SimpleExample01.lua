-- A simple simulation where we create one pedestrian outside the faculty going to the first classroom. 
-- Its walking speed is randomly between 1 - 5 and it is using the behavior FollowingPath

Scenario = {
    Scene="Faculty_1floor",
	CameraConfig = {
		location = {78.28518, 15.088421, 17.921297},
		rotation = {21.01045,-0.05595299,-5.00258E-9},
		lookAt = {78.28518,15.088421,17.921297}
		}
		,
    AgentsDescriptions = {
        population = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class1"
            }
        }
    }
}

Commands:

MassisLua.createHuman("population", 1, "MainGate")
-- HallPrinicpal.Entrada,HallPrinicpal.Biblioteca,PasilloSalonActos.Hall,PasilloAulas.Aula1,
