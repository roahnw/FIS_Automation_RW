package com.qa.factory;

import com.qa.enums.AppConstants;
import com.qa.enums.ConfigKeys;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    private static Properties properties;

    private static void loadPropertiesFile() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(ConfigKeys configKey) {
        if(properties == null) {
            loadPropertiesFile();
        }
        try{
            return (String) properties.get(configKey.toString());
        } catch (NullPointerException e) {
            throw new RuntimeException("Key: "+configKey+" not found in properties file",e);
        }
    }
}
