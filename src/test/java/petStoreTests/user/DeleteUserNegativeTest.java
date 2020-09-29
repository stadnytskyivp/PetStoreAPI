package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.user.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class DeleteUserNegativeTest {
    @Description("Verify that we can't delete non existing user from the store data base")
    @Test
    public void deleteUserTest() {
        Allure.step("Before test making sure that our user don't exist");
        User user = DataSet.addingUser();
        UserClient.postUser(user);
        UserClient.deleteUserByUsername(user.getUsername());

        Allure.step("Start test trying to delete non existing user from the store");
        UserClient.deleteNonExistingUser(user.getUsername());   // checking for 404 status code
    }
}
