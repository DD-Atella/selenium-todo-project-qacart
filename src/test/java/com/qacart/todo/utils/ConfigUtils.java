package com.qacart.todo.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private static ConfigUtils configUtils;
    private Properties prop;
    private ConfigUtils(){
        prop = readProp();
    }
    public static ConfigUtils getInstanse(){
        if(configUtils == null){
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    private Properties readProp(){
        InputStream is;
        try {
            String env =System.getProperty("env","PRODUCTION");
            switch(env){
                case"PRODUCTION" ->{
                    is = new FileInputStream("src/test/resources/env/production.properties");
                }
                case "LOCAL" -> {
                   is = new FileInputStream("src/test/resources/env/local.properties");
                }
                default -> {
                    throw new RuntimeException("Environment is not supported");
                }
            }
            prop = new Properties();
            prop.load(is);
            System.out.println(prop.get("URL"));
        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        return prop;
    }

    public String getBaseURL(){
        return prop.get("URL").toString();
    }
}
