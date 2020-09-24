package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

public class DeleteUserNegativeTest extends AbstractTest {

    @Description("Verify that we can't delete non existing user from the store data base")
    @Test
    public void deleteUserTest() throws IOException {

        LOGGER.info("BEFORE TEST making sure that the base can't delete non existing user");
        UserClient.postUser(DataSet.addingUser());
        UserClient.deleteUserByUsername(DataSet.addingUser().getUsername());
        LOGGER.info("BEFORE TEST USER ADDED AND DELETED");

        LOGGER.info("START TEST delete non existing user from the store data base");
        Response response = UserClient.deleteNonExistingUser(DataSet.addingUser().getUsername());

        Assert.assertTrue(response.asString().trim().isEmpty());
        LOGGER.info("END TEST");
    }
}
