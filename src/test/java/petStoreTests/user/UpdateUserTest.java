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

public class UpdateUserTest extends AbstractTest {

    @Description("Verify that we are editing user data in the store data base")
    @Parameters({"User for editing"})
    @Test(dataProvider = "testData")
    public void updateUserTest(User updatedUser) throws IOException {
        LOGGER.info("BEFORE TEST ADDING USER");
        UserClient.postUser(DataSet.addingUser());
        LOGGER.info("BEFORE TEST USER ADDED");

        LOGGER.info("START TEST edit user info in the store data base");
        ResponseInfo response = UserClient.updateUser(updatedUser);

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), String.valueOf(updatedUser.getId()));

        LOGGER.info("CHECK THE CHANGES");
        User actualUser = UserClient.getUserByUsername(updatedUser.getUsername());

        Assert.assertEquals(updatedUser, actualUser);
        LOGGER.info("END TEST");

        LOGGER.info("AFTER TEST DELETING USER");
        UserClient.deleteUserByUsername(updatedUser.getUsername());
        LOGGER.info("AFTER TEST USER DELETED");
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {DataSet.addingUser().setUsername("Awesome_Black_Potato")},
        };
    }
}
