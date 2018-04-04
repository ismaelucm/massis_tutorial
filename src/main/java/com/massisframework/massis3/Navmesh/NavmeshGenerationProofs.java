package com.massisframework.massis3.Navmesh;

import com.jme3.system.AppSettings;

import java.io.InputStream;
import java.util.Properties;

public class NavmeshGenerationProofs
{
    public static void main (String[] args) throws Exception
    {

        final NavmeshGeneratorTest navmesh = new NavmeshGeneratorTest();
        final AppSettings settings = new AppSettings(true);// vrAppState.getSettings();
        //settings.setSwapBuffers(true);
        //settings.setFrameRate(60);
        navmesh.setSettings(settings);
        navmesh.setShowSettings(false);
        navmesh.start();
    }
}
