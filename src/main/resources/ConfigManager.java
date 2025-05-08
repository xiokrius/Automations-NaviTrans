import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        loadProperties("config.properties");
        loadProperties("config.properties.contacts");
        loadProperties("config.properties.order");
        loadProperties("order.properties");
    }

    private static void loadProperties(String filePath) {
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8)) {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Property '" + key + "' not found");
        }
        // Декодируем ISO-8859-1 → UTF-8 если нужно
        return decodeIfNeeded(value.trim());
    }

    private static String decodeIfNeeded(String value) {
        try {
            // Проверяем, содержит ли строка "кракозябры"
            if (value.matches(".*[Ð-ð].*")) {
                return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            }
            return value;
        } catch (Exception e) {
            return value;
        }
    }
}