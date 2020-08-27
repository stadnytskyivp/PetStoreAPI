package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReusableMethods {

    public static String getBigData() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
            "\\src\\main\\resources\\log4j.properties");
        properties.load(fis);
        return properties.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getBigData());
    }

}