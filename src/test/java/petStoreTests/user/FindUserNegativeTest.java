package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.ResponseInfo;
import dto.requests.user.User;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

public class FindUserNegativeTest extends AbstractTest {
    @Description("Verify that we are can't find user in the store data base")
    @Test
    public void findUserTest() throws IOException {
        LOGGER.info("BEFORE TEST DELETE USER");
        User user = DataSet.addingUser().setUsername("nonExisting");
        UserClient.deleteNonExistingUser(user.getUsername());
        LOGGER.info("BEFORE TEST USER DELETED");

        LOGGER.info("START TEST find user in the store data base");
        ResponseInfo response = UserClient.getNonExistingUser(user.getUsername());

        Assert.assertEquals(response.getCode(), DataSet.messageNotFoundResponse().getCode());
        Assert.assertEquals(response.getType(), DataSet.messageNotFoundResponse().getType());
        Assert.assertEquals(response.getMessage(),
                            DataSet.messageNotFoundResponse().setMessage("User not found").getMessage());
        LOGGER.info("END TEST");
    }
}
