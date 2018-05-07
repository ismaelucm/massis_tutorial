package com.massisframework.massis3.examples.navmesh;

import com.jme3.asset.AssetManager;
import com.jme3.asset.AssetNotFoundException;
import com.jme3.gde.nmgen.NavMeshController;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.massisframework.massis3.commons.spatials.Materials;
import com.massisframework.massis3.commons.spatials.Spatials;

import java.util.ArrayList;
import java.util.List;

public class NavmeshConfiguration
{
    private NavMeshController _navMeshController;
    private Mesh _navMesh;
    private AssetManager _assetManager;
    private Node _parentNode;
    private Material _materialNavGeom;
    private Material _materialNavMeshVertex;


    public NavmeshConfiguration(Node parent, String assetName, AssetManager assetManager, String navmeshMat, ColorRGBA navGeoMatColor, ColorRGBA navMeshVertexMatColor)
    {
        _assetManager = assetManager;
        _parentNode = parent;


        Node resourceNode = (Node) _assetManager.loadModel(assetName);

        _navMeshController = new NavMeshController(resourceNode);
        _navMesh = _navMeshController.generateNavMesh(
        		0.1f, 0.5f, 0.5f,
                0.25f, 48f, true, 0.3f, 5f, true, 
                4, 8, 10f, 0.75f, 4, 25f,
                0.1f, 15f,8,true);
        Geometry navGeom = new Geometry("NavMesh", _navMesh);
        Geometry navMeshVertex = new Geometry("NavMeshVertex", _navMesh);

        _materialNavGeom = new Material(_assetManager,navmeshMat); //"Common/MatDefs/Misc/Unshaded.j3md"
        _materialNavGeom.setColor("Color", navGeoMatColor);
        navGeom.setMaterial(_materialNavGeom);
        resourceNode.attachChild(navGeom);


        navMeshVertex.setMaterial(Materials.newUnshaded(navMeshVertexMatColor, true));
        navMeshVertex.setLocalTranslation(Vector3f.UNIT_Y);
        resourceNode.attachChild(navMeshVertex);

        _parentNode.attachChild(resourceNode);
        /*_parentNode.attachChild(goBox);
        _parentNode.attachChild(goBox2);
        _parentNode.attachChild(goBox3);
        _parentNode.attachChild(goBox4);*/
    }

    private static Geometry createBox(String name, Vector3f position, Vector3f size, ColorRGBA color)
    {
        Box box = new Box(size.getX(),size.getY(),size.getZ());
        Geometry goBox = new Geometry(name, box);
        goBox.setLocalTranslation(position);
        goBox.setMaterial(Materials.newUnshaded(color, false));
        return goBox;
    }

    private static Geometry createSphere(String name, Vector3f position, int radialSample, int zsample, float radious, ColorRGBA color)
    {
        //Sphere(int zSamples, int radialSamples, float radius)
        Sphere sphere = new Sphere(zsample,radialSample,radious);
        //Box box = new Box(size.getX(),size.getY(),size.getZ());
        Geometry goBox = new Geometry(name, sphere);
        goBox.setLocalTranslation(position);
        goBox.setMaterial(Materials.newUnshaded(color, false));
        return goBox;
    }


}
