package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.user.User;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

public class FindUserByUsernameTest extends AbstractTest {

    @Description("Verify that we are can find user in the store data base")
    @Test
    public void findUserTes() throws IOException {

        LOGGER.info("BEFORE TEST ADD USER");
        UserClient.postUser(DataSet.addingUser());
        LOGGER.info("BEFORE TEST USER ADDED");

        LOGGER.info("START TEST find user in the store data base");
        User response = UserClient.getUserByUsername(DataSet.addingUser().getUsername());

        Assert.assertTrue(response.getId() >= 0);
        Assert.assertTrue(response.getUserStatus() >= 0);
        Assert.assertEquals(response.getUsername(), DataSet.addingUser().getUsername());
        Assert.assertEquals(response.getFirstName(), DataSet.addingUser().getFirstName());
        Assert.assertEquals(response.getLastName(), DataSet.addingUser().getLastName());
        Assert.assertEquals(response.getEmail(), DataSet.addingUser().getEmail());
        Assert.assertEquals(response.getPassword(), DataSet.addingUser().getPassword());
        Assert.assertEquals(response.getPhone(), DataSet.addingUser().getPhone());
        LOGGER.info("END TEST");
    }
}
