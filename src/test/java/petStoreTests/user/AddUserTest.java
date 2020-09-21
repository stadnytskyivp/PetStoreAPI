package petStoreTests.user;

import client.StoreClient;
import client.UserClient;
import data.DataSet;
import dto.requests.ResponseInfo;
import dto.requests.store.Order;
import dto.requests.user.User;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import javax.sql.DataSource;
import java.io.IOException;

import static client.StoreClient.deleteOrderById;
import static data.DataSet.*;

public class AddUserTest extends AbstractTest {

    @Description("Verify that we are adding user to the store data base")
    @Parameters({"User for adding"})
    @Test(dataProvider = "testData")
    public void addUserTest(User user) throws IOException {
        LOGGER.info("START TEST add user to the store data base");
        ResponseInfo response = UserClient.postUser(user);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), messageDelResponse().getType());
        Assert.assertEquals(response.getMessage(), String.valueOf(addingUser().getId()));
        LOGGER.info("END TEST");

        LOGGER.info("AFTER TEST DELETING USER");
//        deleteOrderById(response.getId());
        LOGGER.info("AFTER TEST USER DELETED");
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
            {DataSet.addingUser()},
        };
    }
}
