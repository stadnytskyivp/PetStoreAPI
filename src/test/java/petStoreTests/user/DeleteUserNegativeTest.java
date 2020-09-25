package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.user.User;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

public class DeleteUserNegativeTest extends AbstractTest {
    @Description("Verify that we can't delete non existing user from the store data base")
    @Test
    public void deleteUserTest() throws IOException {
        LOGGER.info("BEFORE TEST making sure that the base can't delete non existing user");
        User user = DataSet.addingUser();
        UserClient.postUser(user);
        UserClient.deleteUserByUsername(user.getUsername());
        LOGGER.info("BEFORE TEST USER ADDED AND DELETED");

        LOGGER.info("START TEST delete non existing user from the store data base");
        UserClient.deleteNonExistingUser(user.getUsername());   // checking for 404 status code
        LOGGER.info("END TEST");
    }
}
