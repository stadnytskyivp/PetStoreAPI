package pet;

import client.PetClient;
import dto.requests.pet.ResponseInfo;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static client.PetClient.deletePetById;
import static client.PetClient.postPet;
import static data.PetInfo.addingPet;
import static data.PetInfo.messageNotFoundResponse;

public class FindPetNegativeTest extends AbstractTest {

    @Description("Verify that we will get error message by getting defunct pet by id")
    @Test
    public void getDefunctPetTest() throws IOException {
        LOGGER.info("START TEST find pet in the store");

        LOGGER.info("precondition adding and deleting pet to insure that there won't be any pet with our petID");
        postPet(addingPet());
        deletePetById(addingPet().getId());

        ResponseInfo res = PetClient.getNonExistingPetById(addingPet().getId());

        Assert.assertEquals(res.getCode(), messageNotFoundResponse().getCode());
        Assert.assertEquals(res.getType(), messageNotFoundResponse().getType());
        Assert.assertEquals(res.getMessage(), messageNotFoundResponse().getMessage());

        LOGGER.info("END TEST");
    }

}
