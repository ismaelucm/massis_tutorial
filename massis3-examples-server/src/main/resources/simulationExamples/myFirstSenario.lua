---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by mosi-agil.
--- DateTime: 7/06/18 15:38
---
Scenario = {
    Scene="Faculty_1floor",
    CameraConfig = {
        location = { 70.0, 100.0, 55.0 },
        rotation = { 90.0,0.0,0.0 },
        lookAt = { 0.0, -1.0, 0.0 }
    },
    AgentsDescriptions = {
        ReturnToClass={
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
MassisLua.createHuman("ReturnToClass", 1, "StudentsCafeteria")