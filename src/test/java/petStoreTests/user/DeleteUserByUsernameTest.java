package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.ResponseInfo;
import dto.requests.user.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserByUsernameTest {
    @Description("Verify that we can delete user from the store data base")
    @Test
    public void deleteUserTest() {
        Allure.step("Before test add user");
        User user = DataSet.addingUser();
        UserClient.postUser(user);

        Allure.step("Start test user from the store");
        ResponseInfo response = UserClient.deleteUserByUsername(user.getUsername());

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), user.getUsername());

        UserClient.deleteNonExistingUser(user.getUsername());    // checking for 404 status code
    }
}
