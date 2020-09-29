package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import data.ReusableMethods;
import dto.requests.ResponseInfo;
import dto.requests.user.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddUserTest {
    @Description("Verify that we are adding user to the store data base")
    @Test
    public void addUserTest() {
        Allure.step("Start test add user to the store");
        User user = DataSet.addingUser();
        ResponseInfo response = UserClient.postUser(user);

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertNotNull(response.getMessage());

        User newUser = UserClient.getUserByUsername(user.getUsername());
        ReusableMethods.compareUsers(newUser, user);

        Allure.step("After test deleting user");
        UserClient.deleteUserByUsername(user.getUsername());
    }
}
