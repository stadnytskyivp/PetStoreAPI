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
import java.util.ArrayList;
import java.util.List;

public class CreateListOfUsersTest extends AbstractTest {

    @Description("Verify that we are adding list of users to the store data base")
    @Parameters({"Users for adding"})
    @Test(dataProvider = "testData")
    public void addUserTest(List<User> users) throws IOException {
        LOGGER.info("START TEST add users to the store data base");
        ResponseInfo response = UserClient.postUserList(users);

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), DataSet.messageUnknownResponse().setMessage("ok").getMessage());
        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] testData() {

        List<User> userList = new ArrayList();
        userList.add(DataSet.addingUser().setId(101));
        userList.add(DataSet.addingUser().setId(102));
        userList.add(DataSet.addingUser().setId(103));

        return new Object[][]{
                {userList}
        };
    }
}
