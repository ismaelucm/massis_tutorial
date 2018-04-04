package com.massisframework.massis3.Navmesh;

import com.jme3.app.SimpleApplication;
import com.jme3.gde.nmgen.NavMeshController;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;

import java.io.InputStream;
import java.util.Properties;

public class NavmeshGeneratorTest extends SimpleApplication{

    protected LightingConfiguration _light;
    protected NavmeshConfiguration _navmeshConfig;
    protected String _resurcesPath;

    public NavmeshGeneratorTest() throws Exception
    {
        super();
        Properties properties = new Properties();
        try{
            InputStream in = getClass().getResourceAsStream("/config.properties");
            properties.load(in);
            _resurcesPath = properties.getProperty("host_path");
        }
        catch(Exception e)
        {
            System.out.println("Error to get properties from file system: "+e.getMessage());
            throw new Exception("Ending program...");
        }

    }
    @Override
    public void simpleInitApp() {

        this.getFlyByCamera().setMoveSpeed(10f);
        this.getCamera().setLocation(Vector3f.ZERO);
        _light = new LightingConfiguration(this.getRootNode(),new Vector3f(-0.3f, -0.5f, -0.2f),ColorRGBA.DarkGray);
        _navmeshConfig = new NavmeshConfiguration(this.getRootNode(),_resurcesPath+"buildings/fdi.j3o",assetManager,"Common/MatDefs/Misc/Unshaded.j3md", ColorRGBA.Red);
    }
}
