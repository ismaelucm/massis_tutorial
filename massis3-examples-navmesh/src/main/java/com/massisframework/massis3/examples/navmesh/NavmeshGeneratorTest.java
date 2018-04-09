package com.massisframework.massis3.examples.navmesh;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.system.JmeSystem;

import java.net.MalformedURLException;
import java.net.URL;

public class NavmeshGeneratorTest extends SimpleApplication{

    protected LightingConfiguration _light;
    protected NavmeshConfiguration _navmeshConfig;

    public NavmeshGeneratorTest() throws Exception
    {
        super();
    }
    @Override
    public void simpleInitApp() {

        //JmeSystem.setLowPermissions(true);;
        //this.assetManager.registerLocator("/home/mosi-agil/Documentos/mosi-agil/massis3-4-examples/massis3-examples-navmesh/assets/",ClasspathLocator.class);
        this.getFlyByCamera().setMoveSpeed(20f);
        this.getCamera().setLocation(new Vector3f(0, 100, 0));
        this.getCamera().lookAt(Vector3f.ZERO,Vector3f.UNIT_Y);
        _light = new LightingConfiguration(this.getRootNode(),new Vector3f(-0.3f, -0.5f, -0.2f),ColorRGBA.DarkGray);
        _navmeshConfig = new NavmeshConfiguration(this.getRootNode(),"assets/Scenes/Faculty_1floor/Faculty_1floor.j3o",this.assetManager,"Common/MatDefs/Misc/Unshaded.j3md", ColorRGBA.Blue, ColorRGBA.Red);
    }
}
