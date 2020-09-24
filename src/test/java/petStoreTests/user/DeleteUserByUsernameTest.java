package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.ResponseInfo;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

public class DeleteUserByUsernameTest extends AbstractTest {

    @Description("Verify that we can delete user from the store data base")
    @Test
    public void deleteUserTest() throws IOException {

        LOGGER.info("BEFORE TEST ADD USER");
        UserClient.postUser(DataSet.addingUser());
        LOGGER.info("BEFORE TEST USER ADDED");

        LOGGER.info("START TEST delete user from the store data base");
        ResponseInfo response = UserClient.deleteUserByUsername(DataSet.addingUser().getUsername());

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), DataSet.addingUser().getUsername());
        LOGGER.info("END TEST");
    }
}
