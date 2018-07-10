-- This simulation creates ten groups of pedestrian, five of them in the main gate and another five in the back gate.
-- All groups walking with different speeds between 1-5 and with the destination at the different classrooms.
-- All they using the behavior FollowingPath
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {

        {
            location = { 33.0, 50.0, 54.0 },
            rotation = { 90.0, 0.0, 0.0 },
            lookAt = { 0.0, 0.0, 0.0 }
        },
        {
        location = {90.75225, 26.890236, 25.397486},
        rotation = {41.377083,0.5595291,1.6008256E-7},
        lookAt = {90.75225,26.890236,25.397486}
        },
        {
            location = {23.008701, 17.934904, 26.059069},
            rotation = {33.3199,0.44762328,2.6680428E-7},
            lookAt = {23.008701,17.934904,26.059069}
        },
        {
            location = {48.18943, 7.921671, 39.804356},
            rotation = {-8.420923,84.17573,-3.841981E-6},
            lookAt = {48.18943,7.921671,39.804356}
        }
    },
    AgentsDescriptions = {
        Class1 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class1"
            }
        },
        Class2 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class2"
            }
        },
        Class3 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class3"
            }
        },
        Class4 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class4"
            }
        },
        Class5 = {
            behavior = "FollowingPath",
            SpeedMin = 1.0,
            SpeedMax = 5.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "Class5"
            }
        }
    }
}

Commands:

MassisLua.createHuman("Class1", 25, "MainGate")
MassisLua.createHuman("Class1", 25, "BackGate")
MassisLua.createHuman("Class2", 20, "MainGate")
MassisLua.createHuman("Class2", 20, "BackGate")
MassisLua.createHuman("Class3", 15, "MainGate")
MassisLua.createHuman("Class3", 20, "BackGate")
MassisLua.createHuman("Class4", 25, "MainGate")
MassisLua.createHuman("Class4", 20, "BackGate")
MassisLua.createHuman("Class5", 25, "MainGate")
MassisLua.createHuman("Class5", 15, "BackGate")
