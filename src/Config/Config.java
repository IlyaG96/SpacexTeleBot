package Config;

import Exceptions.NullValueException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream(".properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new NullValueException("Set property '%s' in .properties file or check that .properties file exist".formatted(key));
        }
        return properties.getProperty(key);
    }
}
