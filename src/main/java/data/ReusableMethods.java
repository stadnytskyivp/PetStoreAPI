package data;

import dto.requests.user.User;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class ReusableMethods {
    public static String getRandomUsername() {
        Random random = new Random();
        return Long.toString(Math.abs(random.nextLong() % 3656158440062976L), 36);
    }

    public static String getBigData() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
            "/src/main/resources/log4j.properties");
        properties.load(fis);
        return properties.toString();
    }

    public static void compareUsers(User newUser, User expectedUser){
        Assert.assertNotNull(newUser);
        Assert.assertNotNull(newUser.getUserStatus());
        Assert.assertEquals(newUser.getUsername(), expectedUser.getUsername());
        Assert.assertEquals(newUser.getFirstName(), expectedUser.getFirstName());
        Assert.assertEquals(newUser.getLastName(), expectedUser.getLastName());
        Assert.assertEquals(newUser.getEmail(), expectedUser.getEmail());
        Assert.assertEquals(newUser.getPassword(), expectedUser.getPassword());
        Assert.assertEquals(newUser.getPhone(), expectedUser.getPhone());
    }
}
