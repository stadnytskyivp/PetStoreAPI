package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class ReusableMethods {

    public static String getRandomUsername() {
        Random random = new Random();
        return Long.toString(Math.abs(random.nextLong() % 3656158440062976L), 36);
    }

    public static int getRandomNumber(){
        return (int) (Math.random() * 1000000) ;
    }

    public static String getBigData() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
            "/src/main/resources/log4j.properties");
        properties.load(fis);
        return properties.toString();
    }
}
