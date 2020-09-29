package petStoreTests.user;

import client.UserClient;
import data.DataSet;
import data.ReusableMethods;
import dto.requests.user.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FindUserByUsernameTest {
    @Description("Verify that we are can find user in the store data base")
    @Parameters({"User for adding and finding"})
    @Test(dataProvider = "testData")
    public void findUserTest(User expectedUser) {
        Allure.step("Before test add user");
        UserClient.postUser(expectedUser);

        Allure.step("Start test find user in the store data base");
        User actualUser = UserClient.getUserByUsername(expectedUser.getUsername());

        ReusableMethods.compareUsers(actualUser, expectedUser);
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
