HumanDescriptions = {
    populationClass1 = {
        behavior = "FollowingPath",
        SpeedMin = 1.0,
        SpeedMax = 8.0,
        AnimationSpeedReference = 4.0,
        RewriteParameter = {
            Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,Class1"
        }
    },
    populationClass2 = {
        behavior = "FollowingPath",
        SpeedMin = 1.0,
        SpeedMax = 8.0,
        AnimationSpeedReference = 4.0,
        RewriteParameter = {
            Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,Class2"
        }
    },
    populationClass3 = {
        behavior = "FollowingPath",
        SpeedMin = 1.0,
        SpeedMax = 8.0,
        AnimationSpeedReference = 4.0,
        RewriteParameter = {
            Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,Class3"
        }
    },
    populationClass4 = {
        behavior = "FollowingPath",
        SpeedMin = 1.0,
        SpeedMax = 8.0,
        AnimationSpeedReference = 4.0,
        RewriteParameter = {
            Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,ClassesLobby.Class4,Class4"
        }
    },
    populationClass5 = {
        behavior = "FollowingPath",
        SpeedMin = 1.0,
        SpeedMax = 8.0,
        AnimationSpeedReference = 4.0,
        RewriteParameter = {
            Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,ClassesLobby.Class4,ClassesLobby.Class5,Class5"
        }
    }
}

Commands :

MassisLua.createHuman("populationClass1", 25, "MainGate")
MassisLua.createHuman("populationClass2", 25, "MainGate")
MassisLua.createHuman("populationClass3", 25, "MainGate")
MassisLua.createHuman("populationClass4", 25, "MainGate")
MassisLua.createHuman("populationClass5", 25, "MainGate")
