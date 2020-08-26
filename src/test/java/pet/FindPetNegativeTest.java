package pet;

import client.PetClient;
import dto.requests.pet.ResponseInfo;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.addingPet;
import static data.PetInfo.messageNotFoundResponse;

public class FindPetNegativeTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(FindPetNegativeTest.class);

    @Test
    public void getDefunctPetTest() throws IOException {
        LOGGER.info("START TEST find pet in the store");

        LOGGER.info("precondition adding and deleting pet to insure that there won't be any pet with our petID");
        AddPetTest.addPetTest(addingPet());
        DeletePet.deletePetTest();

        Response res = PetClient.getPetById(addingPet().getId());
        ResponseInfo response = res.as(ResponseInfo.class);

        Assert.assertEquals(response.getCode(), messageNotFoundResponse().getCode());
        Assert.assertEquals(response.getType(), messageNotFoundResponse().getType());
        Assert.assertEquals(response.getMessage(), messageNotFoundResponse().getMessage());

        LOGGER.info("END TEST");
    }

}
