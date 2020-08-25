package pet;

import client.PetClient;
import dto.requests.pet.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.messageResponse;
import static data.PetInfo.addingPet;

public class DeletePet {

    public static final Logger LOGGER = LoggerFactory.getLogger(DeletePet.class);

    @Test(priority = 1)
    public void deletePetTest() throws IOException {
        LOGGER.info("START TEST delete pet from the store");

        ResponseInfo response = PetClient.deletePetById(addingPet().getId());

        Assert.assertEquals(response.getType(), messageResponse().getType());
        Assert.assertEquals(response.getMessage(), messageResponse().getMessage());

        LOGGER.info("END TEST");
    }

    @Test(priority = 2)
    public void deleteDefunctPetTest() throws IOException {
        LOGGER.info("START TEST delete pet defunct from the store");

        ResponseInfo response = PetClient.deletePetById(addingPet().getId());

        Assert.assertNull(response);

        LOGGER.info("END TEST");
    }

}
