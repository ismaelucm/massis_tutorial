package com.massisframework.massis3.examples.simulation;

import java.io.IOException;

public final class MainUtils {

    public static String getCurrentPath()
    {
        String current = null;
        try {
            current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Path=>"+current);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return current;
    }
}
