package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties prop = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("config/config.properties");
            prop.load(file);
        } catch (IOException e) {
            throw new RuntimeException("config.properties file not found.");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    public static int getInt(String key) {
        String value = prop.getProperty(key);
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            throw new RuntimeException("Key '" + key + "' not found in config.properties.");
        }
    }

    public static boolean getBoolean(String key) {
        String value = prop.getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        } else {
            throw new RuntimeException("Key '" + key + "' not found in config.properties.");
        }
    }

    public static String getUrl(String key) {
        String url = prop.getProperty(key);
        if (url != null && !url.isEmpty()) {
            return url;
        } else {
            throw new RuntimeException("URL for key '" + key + "' not found in config.properties.");
        }
    }
}
