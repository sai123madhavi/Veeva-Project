package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("❌ config.properties not found in classpath");
            }

            prop.load(input);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = prop.getProperty(key);

        if (value == null) {
            throw new RuntimeException("❌ Key not found in config: " + key);
        }

        return value;
    }
}