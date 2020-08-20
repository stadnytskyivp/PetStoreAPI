package pet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AbstractTest {

    public static String getBaseUrl(String hostName) throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
            "\\src\\main\\resources\\env.properties");
        properties.load(fis);

        return properties.getProperty(hostName);
    }

}
