package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException("Could not load configuration file", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}