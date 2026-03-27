package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    /**
     * Reads a value from config.properties using a key
     * Returns null if the file or the key does not exist
     *
     * @param key property name (for example, "base.url", "wait")
     * @return property value or null
     */
    private static String read(String key) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = Files.newInputStream(Paths.get("config.properties"));
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("config.properties file loading error" + e.getMessage());
        }
        return properties.getProperty(key);

    }

    /**
     * Gets a property value as String
     *
     * @param key property name
     * @return property value as String
     */
    public static String get(String key) {
        return read(key);

    }

    /**
     * Gets a property value as Long
     * Used for values like wait time
     *
     * @param key property name (for example, "wait")
     * @return property value as Long
     */
    public static Long getLong(String key) {
        return Long.parseLong(read(key));

    }
}
