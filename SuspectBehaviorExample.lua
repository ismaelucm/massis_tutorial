HumanDescriptions = {
    Thief = {
        behavior = "Thief",
        tag = "Thief",
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
    Victims = {
        behavior = "FollowingPath",
        tag = "Victims",
        RewriteParameter = {
            Path = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,MainHall.Library",
            IsCircularPath = true
        }
    },
    Security = {
        behavior = "Security",
        tag = "Security",
        RewriteParameter = {
            PathToPatrol = "MainHall.Entrance,MainHall.Library,LobbyEventRoom.Hall,MainHall.Library",
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

Commands :

MassisLua.createHuman("Thief", 1, "MainGate")
MassisLua.createHuman("Victims", 2, "MainHall.Entrance")
MassisLua.createHuman("Security", 1, "MainHall.Library")
MassisLua.createHumanDeferred("Security", 1, "MainGate", 10)