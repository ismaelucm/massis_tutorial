package com.massisframework.massis3.examples.navmesh;

import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;


public class LightingConfiguration {
    protected DirectionalLight _directionalLight;
    protected Node _parent;
    protected AmbientLight _ambientLight;

    public LightingConfiguration(Node parent, Vector3f lightDirection, ColorRGBA ambientColor) {
        _parent = parent;
        _directionalLight = new DirectionalLight();
        _directionalLight.setDirection(lightDirection);
        _parent.addLight(_directionalLight);

        _ambientLight = new AmbientLight();
        _ambientLight.setColor(ambientColor);
        _parent.addLight(_ambientLight);
    }
}
