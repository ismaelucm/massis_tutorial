package com.massisframework.massis3.examples.navmesh;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeSystem;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class NavmeshGenerationProofs
{
    public static void main (String[] args) throws Exception
    {

        final NavmeshGeneratorTest navmesh = new NavmeshGeneratorTest();
        final AppSettings settings = new AppSettings(true);// vrAppState.getSettings();
        settings.setAudioRenderer(null); // to avoid failures when audio is not present
      
        navmesh.setSettings(settings);
        navmesh.setShowSettings(false);

        navmesh.start();
    }
}
