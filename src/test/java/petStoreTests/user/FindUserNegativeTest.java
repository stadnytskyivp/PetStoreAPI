package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.ResponseInfo;
import dto.requests.user.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindUserNegativeTest {
    @Description("Verify that we are can't find user in the store data base")
    @Test
    public void findUserTest() {
        Allure.step("Before test delete user");
        User user = DataSet.addingUser().setUsername("nonExisting");
        UserClient.deleteNonExistingUser(user.getUsername());

        Allure.step("Start test find user in the store");
        ResponseInfo response = UserClient.getNonExistingUser(user.getUsername());

        Assert.assertEquals(response.getCode(), DataSet.messageNotFoundResponse().getCode());
        Assert.assertEquals(response.getType(), DataSet.messageNotFoundResponse().getType());
        Assert.assertEquals(response.getMessage(),
            DataSet.messageNotFoundResponse().setMessage("User not found").getMessage());
    }
}
