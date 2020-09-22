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

public class AddUserTest extends AbstractTest {

    @Description("Verify that we are adding user to the store data base")
    @Parameters({"User for adding"})
    @Test(dataProvider = "testData")
    public void addUserTest(User user) throws IOException {
        LOGGER.info("START TEST add user to the store data base");
        ResponseInfo response = UserClient.postUser(user);

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), String.valueOf(user.getId()));
        LOGGER.info("END TEST");

        LOGGER.info("AFTER TEST DELETING USER");
        UserClient.deleteUserByUsername(user.getUsername());
        LOGGER.info("AFTER TEST USER DELETED");
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
            {DataSet.addingUser()},
        };
    }
}
