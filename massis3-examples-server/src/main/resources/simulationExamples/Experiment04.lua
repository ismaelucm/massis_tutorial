-- This simulation creates ten groups of pedestrian, five of them in the main gate and another five in the back gate.
-- All groups walking with different speeds between 1-5 and with the destination at the different classrooms.
-- All they using the behavior FollowingPath
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 91.36081, 25.665495, 67.48593 },
        rotation = { 90.0,0.0,0.0 },
        lookAt = { 0.0, -1.0, 0.0 }
    },
    AgentsDescriptions = {
        GotoClass1 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,Class1"
            }
        },
        GotoClass2 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,Class2"
            }
        },
        GotoClass3 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,ClassesLobby.Class1,ClassesLobby.Class2,ClassesLobby.Class3,Class3"
            }
        },
        GotoClass4 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "HallBackGate.Lobby,ClassesLobby.Class5,ClassesLobby.Class4,Class4"
            }
        },
        GotoClass5 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "HallBackGate.Lobby,ClassesLobby.Class5,Class5"
            }
        },
        ExitClass1 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class1,ElevatorsLobby,LobbyCafeteria,MainHall.Entrance,MainGate";
            }
        },
        ExitClass2 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class2,ClassesLobby.Class1,LobbyEventRoom.Hall,MainHall.Library,MainHall.Entrance,MainGate";
            }
        },
        ExitClass3 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class3,ClassesLobby.Class2,ClassesLobby.Class1,ElevatorsLobby,LobbyCafeteria,MainHall.Entrance,MainGate";
            }
        },
        ExitClass4 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class4,ClassesLobby.Class5,HallBackGate.Lobby,BackGate";
            }
        },
        ExitClass5 = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "ClassesLobby.Class5,HallBackGate.Lobby,BackGate"
            }
        },
        EnterToCaffeterieMain = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "MainHall.Entrance,LobbyCafeteria,CafeteriaHall,ProfessorsCafeteria"
            }
        },
        CaffeterieExitWithMain = {
            behavior = "FollowingPath",
            Speed=4.0,
            AnimationSpeedReference = 4.0,
            RewriteParameter = {
                Path = "CafeteriaHall,LobbyCafeteria,MainHall.Entrance,MainGate"
            }
        }
    }
}

Commands:

MassisLua.createHuman("GotoClass1", 20, "MainGate")
MassisLua.createHuman("GotoClass2", 20, "MainGate")
MassisLua.createHuman("GotoClass3", 20, "MainGate")
MassisLua.createHuman("GotoClass4", 20, "BackGate")
MassisLua.createHuman("GotoClass5", 20, "BackGate")

MassisLua.createHuman("ExitClass1", 20, "Class1")
MassisLua.createHuman("ExitClass2", 20, "Class2")
MassisLua.createHuman("ExitClass3", 20, "Class3")
MassisLua.createHuman("ExitClass4", 20, "Class4")
MassisLua.createHuman("ExitClass5", 20, "Class5")

MassisLua.createHuman("EnterToCaffeterieMain", 20, "MainGate")
MassisLua.createHuman("CaffeterieExitWithMain", 20, "ProfessorsCafeteria")



