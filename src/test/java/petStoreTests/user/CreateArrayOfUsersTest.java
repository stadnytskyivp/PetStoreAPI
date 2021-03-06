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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateArrayOfUsersTest {
    @Description("Verify that we are adding array of users to the store data base")
    @Parameters({"Users for adding"})
    @Test(dataProvider = "testData")
    public void addUserTest(User[] users) {
        Allure.step("Before test deleting users");
        if (users.length != 0) {
            for (User user : users) {
                UserClient.deleteNonCheckedUser(user.getUsername());
            }
        }

        Allure.step("Start test add users to the store");
        ResponseInfo response = UserClient.postUserArray(users);

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), DataSet.messageUnknownResponse().setMessage("ok").getMessage());

        if (users.length != 0) {
            for (User user : users) {
                User actualUser = UserClient.getUserByUsername(user.getUsername());
                ReusableMethods.compareUsers(actualUser, user);
            }
        }
    }

    @DataProvider
    public Object[][] testData() {
        User user1 = DataSet.addingUser();
        User user2 = DataSet.addingUser();
        User user3 = DataSet.addingUser();
        User[] treeUsers = new User[]{user1, user2, user3};
        User[] oneUser = new User[]{user1};

        return new Object[][]{
            {oneUser},
            {treeUsers},
            {new User[]{}}
        };
    }
}
