package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        loadProperties("src/resources/config.properties");
        loadProperties("src/resources/config.properties.contacts");
    }

    private static void loadProperties(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось загрузить файл: " + filePath);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}