package schwimmer.weather;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Retrieve the API Key in apikey.properties or from env variable on GitHub
 */
public class ApiKey {

    private final String key;

    public ApiKey() {
        Properties properties = new Properties();
        InputStream in = ApiKey.class.getResourceAsStream(
                "/apikey.properties");
        try {
            if (in != null) {
                properties.load(in);
                key = properties.getProperty("apikey");
            }
            else {
                key = "fix this later";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return key;
    }
}
