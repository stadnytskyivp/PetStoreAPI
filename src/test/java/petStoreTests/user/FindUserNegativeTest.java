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

public class FindUserNegativeTest extends AbstractTest {
    @Description("Verify that we are can't find user in the store data base")
    @Parameters({"User for deleting and finding"})
    @Test(dataProvider = "testData")
    public void findUserTest(User user) throws IOException {
        LOGGER.info("BEFORE TEST DELETE USER");
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

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
            {DataSet.addingUser().setUsername("nonExisting")},
        };
    }
}
