package com.massisframework.massis3.examples.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.InputStream;
import java.util.Properties;

public final class Configuration
{
    private static final Logger log = LoggerFactory.getLogger(Configuration.class);
    private String _host;
    private String _path;
    private int _port;
    private Configuration() throws Exception {
        Properties properties = new Properties();
        try{
            InputStream in = getClass().getResourceAsStream("/config.properties");
            properties.load(in);
            _port = Integer.parseInt(properties.getProperty("host_port"));
            _host = properties.getProperty("host_ip");
            _path = properties.getProperty("host_path");
        }
        catch(Exception e)
        {
            log.error("Error to get properties from file system: "+e.getMessage());
            throw new Exception("Ending program...");
        }
    }

    private static Configuration _instance = null;

    public static Configuration instance() throws Exception
    {
        if(_instance == null)
            _instance = new Configuration();

        return _instance;
    }

    public String getHost()
    {
        return _host;
    }

    public int getPort()
    {
        return _port;
    }

    public String getPath()
    {
        return _path;
    }

}
