package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось загрузить файл config.properties");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}