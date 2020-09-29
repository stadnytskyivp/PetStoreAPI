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

public class UpdateUserTest {
    @Description("Verify that we are editing user data in the store data base")
    @Parameters({"User for editing"})
    @Test(dataProvider = "testData")
    public void updateUserTest(User expectedUser) {
        Allure.step("Before test adding user");
        UserClient.deleteNonCheckedUser(expectedUser.getUsername());
        User oldUser = DataSet.addingUser();
        UserClient.postUser(oldUser);

        Allure.step("Start test edit user info");
        ResponseInfo response = UserClient.updateUser(oldUser.getUsername(), expectedUser.setId(oldUser.getId()));

        Assert.assertEquals(response.getCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getType(), DataSet.messageUnknownResponse().getType());
        Assert.assertNotNull(response.getMessage());

        Allure.step("Check the changes");
        User actualUser = UserClient.getUserByUsername(expectedUser.getUsername());

        ReusableMethods.compareUsers(actualUser, expectedUser);

        Allure.step("After test deleting user");
        UserClient.deleteUserByUsername(actualUser.getUsername());
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
            {DataSet.addingUser().setUsername("Awesome_Black_Potato")},
            {DataSet.addingUser().setFirstName("Carlos")},
            {DataSet.addingUser().setLastName("Lopez")},
            {DataSet.addingUser().setPassword("password92")},
            {DataSet.addingUser().setEmail("some@email.com")},
            {DataSet.addingUser().setPhone("+1488")},
            {DataSet.addingUser().setUserStatus(10)},
        };
    }
}
