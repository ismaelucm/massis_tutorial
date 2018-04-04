package com.massisframework.massis3.Navmesh;

import com.jme3.asset.AssetManager;
import com.jme3.asset.AssetNotFoundException;
import com.jme3.gde.nmgen.NavMeshController;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;

public class NavmeshConfiguration
{
    private NavMeshController _navMeshController;
    private Mesh _navMesh;
    private AssetManager _assetManager;
    private Node _parentNode;
    private Material _material;


    public NavmeshConfiguration(Node parent, String assetPath, AssetManager assetManager, String navmeshMat, ColorRGBA matColor)
    {
        _assetManager = assetManager;
        _parentNode = parent;

        Node resourceNode = (Node) _assetManager.loadModel(assetPath);


        _navMeshController = new NavMeshController(resourceNode);
        _navMesh = _navMeshController.generateNavMesh(0.15f, 0.5f, 1.5f,
                0.25f, 48f, true, 0.1f, 2f, true, 3, 1, 0f, 0.25f, 6, 100,
                0.1f);
        Geometry navGeom = new Geometry("NavMesh", _navMesh);

        _material = new Material(_assetManager,navmeshMat); //"Common/MatDefs/Misc/Unshaded.j3md"
        _material.setColor("Color", matColor);
        navGeom.setMaterial(_material);
        resourceNode.attachChild(navGeom);
        _parentNode.attachChild(resourceNode);
    }


}
