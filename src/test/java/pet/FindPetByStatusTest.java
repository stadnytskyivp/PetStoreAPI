package pet;

import client.PetClient;
import dto.requests.EStatus;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class FindPetByStatusTest extends AbstractTest {

    @Story("Searching pets by status")
    @Description("Verify that we are getting pets by the right status")
    @Parameters({"Status for searching"})
    @Test(dataProvider = "statusData")
    public void getPetsByStatusTest(EStatus status) throws IOException {
        LOGGER.info("START TEST find pets in the store by status");

        Response res = PetClient.getPetByStatus(status);

        String strResponse = res.asString();
        JsonPath jsonPath = new JsonPath(strResponse);
        List<String> statusArr = jsonPath.getJsonObject("status");

        for (String str : statusArr) {
            Assert.assertEquals(str, status.toString());
        }

        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] statusData() {
        return new Object[][]{
            {EStatus.AVAILABLE},
            {EStatus.PENDING},
            {EStatus.SOLD},
        };
    }
}
