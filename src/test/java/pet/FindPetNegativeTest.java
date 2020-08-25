package pet;

import client.PetClient;
import data.PetInfo;
import dto.requests.pet.ResponseInfo;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class FindPetNegativeTest {

    private static final int DEFUNCT_PET_ID = 666666;

    public static final Logger LOGGER = LoggerFactory.getLogger(FindPetNegativeTest.class);

    @Test(dataProvider = "defunctPet")
    public void getDefunctPetTest(ResponseInfo messageInfo) throws IOException {
        LOGGER.info("START TEST find pet in the store");

        Response res = PetClient.getPetById(DEFUNCT_PET_ID, 404);
        ResponseInfo response = res.as(ResponseInfo.class);

        Assert.assertEquals(response.getCode(), messageInfo.getCode());
        Assert.assertEquals(response.getType(), messageInfo.getType());
        Assert.assertEquals(response.getMessage(), messageInfo.getMessage());

        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] defunctPet() {
        return new Object[][]{
            {PetInfo.messageResponse().setCode(1).setType("error").setMessage("Pet not found")}
        };
    }

}
