package com.massisframework.massis3.examples.navmesh;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLocator;
import com.jme3.asset.AssetManager;

import java.io.InputStream;

public class MyAssetLoader implements AssetLocator {

    String path;

    @Override
    public void setRootPath(String rootPath) {
        path = rootPath;
    }

    @Override
    public AssetInfo locate(AssetManager manager, AssetKey key) {
        AssetInfo info = new MyAssetInfo(manager, key);
        InputStream stream = info.openStream();

        return info;
    }
}
