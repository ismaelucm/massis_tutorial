package com.massisframework.massis3.examples.navmesh;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;

import java.io.InputStream;

public class MyAssetInfo extends AssetInfo {
    public MyAssetInfo(AssetManager manager, AssetKey key) {
        super(manager, key);
    }

    @Override
    public InputStream openStream() {
        return null;
    }
}
