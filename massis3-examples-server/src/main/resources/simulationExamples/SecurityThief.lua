-- The simulation has three profiles: Victims, Thief and Security. 
-- The security patrolling around the hall.
-- The victims walking around the elevator. Use the behavior FollowingPath.
-- the thief appears at 20 seconds and select a victim randomly. Use the behavior Thief.
-- the security agent sees the thief and this is suspicious enough (in this case it is) he chase him. Use the behavior Security.
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 100.0, 140.0, 56.0 },
        rotation = {    90.0, 0.0, 0.0 },
        lookAt = { 100.0, 0.0, 56.0 }
    },
    AgentsDescriptions = {
        Thief = {
            behavior = "Thief",
            tag = "Thief",
            Speed = 4.0,
            RewriteParameter = {
                TagToSearch = "Victims",
                PlaceListToRunAway = "MainGate,BackGate"
            },
            Components = {
                Personality = {
                    suspiciousFactor = 0.5
                }
            }
        },
        Victims01 = {
            behavior = "FollowingPathAgent",
            Speed = 2.0,
            tag = "Victims",
            RewriteParameter = {
                Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ElevatorsLobby,CafeteriaHall",
                IsCircularPath = true
            }
        },
        Victims02 = {
            behavior = "FollowingPathAgent",
            Speed = 2.0,
            tag = "Victims",
            RewriteParameter = {
                Path = "ElevatorsLobby,ClassesLobby.Class1,LobbyEventRoom.Hall,MainHall.Library,MainHall.Entrance,MainGate",
                IsCircularPath = true
            }
        },
        Security = {
            behavior = "Security",
            tag = "Security",
            Speed = 4.0,
            RewriteParameter = {
                PathToPatrol = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall",
                SuspuciousLevel = 0.7
            },
            Components = {
                Perception = {
                    visionAngle = 160.0,
                    visionSqrDisance = 100.0
                }
            }
        }
    }
}

Commands:

--MassisLua.createHumanDeferred("Thief",1,"MainGate",20)
MassisLua.createHuman("Victims01", 50, "MainGate")
MassisLua.createHuman("Victims02", 50, "CafeteriaHall")
--MassisLua.createHuman("Security",1,"MainHall.Library")
