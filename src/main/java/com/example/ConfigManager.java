package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        loadProperties("config.properties");
        loadProperties("config.properties.contacts");
        loadProperties("config.properties.order");
        loadProperties("order.properties");
    }

    private static void loadProperties(String fileName) {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Файл не найден: " + fileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки файла: " + fileName, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}