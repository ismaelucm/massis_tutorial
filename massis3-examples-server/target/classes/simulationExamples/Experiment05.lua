Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 91.36081, 25.665495, 67.48593 },
        rotation = { 90.0,0.0,0.0 },
        lookAt = { 0.0, -1.0, 0.0 }
    },
    AgentsDescriptions = {
        Class1 = {
            behavior = "FollowingPath",
            Speed=2.0,
            AnimationSpeedReference = 2.0,
            RewriteParameter = {
                Path = "HallBackGate.Lobby,ClassesLobby.Class5,ClassesLobby.Class4,ClassesLobby.Class3,ClassesLobby.Class2,ClassesLobby.Class1,Class1"
            }
        },
        Class2 = {
            behavior = "FollowingPath",
            Speed=2.0,
            AnimationSpeedReference = 2.0,
            RewriteParameter = {
                Path = "HallBackGate.Lobby,ClassesLobby.Class5,ClassesLobby.Class4,ClassesLobby.Class3,ClassesLobby.Class2,Class2"
            }
        },
        Class3 = {
            behavior = "FollowingPath",
            Speed=2.0,
            AnimationSpeedReference = 2.0,
            RewriteParameter = {
                Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,Class3"
            }
        },
        Class4 = {
            behavior = "FollowingPath",
            Speed=2.0,
            AnimationSpeedReference = 2.0,
            RewriteParameter = {
                Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,ClassesLobby.Class4,Class4"
            }
        },
        Class5 = {
            behavior = "FollowingPath",
            Speed=2.0,
            AnimationSpeedReference = 2.0,
            RewriteParameter = {
                Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,ClassesLobby.Class4,ClassesLobby.Class5,Class5"
            }
        }
    }
}

Commands:

MassisLua.createHuman("Class1", 40, "BackGate")
MassisLua.createHuman("Class2", 40, "BackGate")
MassisLua.createHuman("Class3", 40, "MainGate")
MassisLua.createHuman("Class4", 40, "MainGate")
MassisLua.createHuman("Class5", 40, "MainGate")

MassisLua.createHumanDeferred("Class1", 40, "BackGate",90)
MassisLua.createHumanDeferred("Class2", 40, "BackGate",90)
MassisLua.createHumanDeferred("Class3", 40, "MainGate",90)
MassisLua.createHumanDeferred("Class4", 40, "MainGate",90)
MassisLua.createHumanDeferred("Class5", 40, "MainGate",90)