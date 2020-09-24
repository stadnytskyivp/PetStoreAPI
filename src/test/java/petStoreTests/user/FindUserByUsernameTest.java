package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import dto.requests.user.User;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

public class FindUserByUsernameTest extends AbstractTest {
    @Description("Verify that we are can find user in the store data base")
    @Parameters({"User for adding and finding"})
    @Test(dataProvider = "testData")
    public void findUserTest(User user) throws IOException {
        LOGGER.info("BEFORE TEST ADD USER");
        UserClient.postUser(user);
        LOGGER.info("BEFORE TEST USER ADDED");

        LOGGER.info("START TEST find user in the store data base");
        User response = UserClient.getUserByUsername(user.getUsername());

        Assert.assertTrue(response.getId() >= 0);
        Assert.assertTrue(response.getUserStatus() >= 0);
        Assert.assertEquals(response.getUsername(), user.getUsername());
        Assert.assertEquals(response.getFirstName(), user.getFirstName());
        Assert.assertEquals(response.getLastName(), user.getLastName());
        Assert.assertEquals(response.getEmail(), user.getEmail());
        Assert.assertEquals(response.getPassword(), user.getPassword());
        Assert.assertEquals(response.getPhone(), user.getPhone());
        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
            {DataSet.addingUser()},
            {DataSet.addingUser().setId(0L)},
            {DataSet.addingUser().setId(null)},
            {DataSet.addingUser().setId(9223372036854775807L)},
            {DataSet.addingUser().setUsername("ячщЯЧЩ123№*(♀♪♂")},
            {DataSet.addingUser().setFirstName(null)},
            {DataSet.addingUser().setLastName(null)},
            {DataSet.addingUser().setEmail(null)},
            {DataSet.addingUser().setEmail("123")},
            {DataSet.addingUser().setPhone(null)},
            {DataSet.addingUser().setPhone("abcdefg")},
            {DataSet.addingUser().setPassword(null)},
            {DataSet.addingUser().setUserStatus(0)},
        };
    }
}
