package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.ResponseInfo;
import dto.requests.user.User;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateListOfUsersTest extends AbstractTest {
    @Description("Verify that we are adding list of users to the store data base")
    @Parameters({"Users for adding"})
    @Test(dataProvider = "testData")
    public void addUserTest(List<User> users) throws IOException {
        LOGGER.info("BEFORE TEST DELETING users");
        if (users.size() != 0){
            for (User user : users) {
                UserClient.deleteUserByUsername(user.getUsername());
            }
        }
        LOGGER.info("BEFORE TEST users DELETED");

        LOGGER.info("START TEST add users to the store data base");
        ResponseInfo response = UserClient.postUserList(users);

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), DataSet.messageUnknownResponse().setMessage("ok").getMessage());

        if (users.size() != 0){
            for (User user : users) {
                User actualUser = UserClient.getUserByUsername(user.getUsername());
                Assert.assertEquals(actualUser.getUsername(), user.getUsername());
            }
        }
        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] testData() {
        User user1 = DataSet.addingUser().setUsername("Leyline_Tyrant1").setId(101L);
        User user2 = DataSet.addingUser().setUsername("Leyline_Tyrant2").setId(102L);
        User user3 = DataSet.addingUser().setUsername("Leyline_Tyrant3").setId(103L);
        User[] treeUsers = new User[]{user1, user2, user3};
        User[] oneUser = new User[]{user1};

        return new Object[][]{
            {Arrays.asList(treeUsers)},
            {Arrays.asList(oneUser)},
            {Collections.emptyList()}
        };
    }
}
