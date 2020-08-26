package pet;

import client.PetClient;
import dto.requests.pet.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.addingPet;
import static data.PetInfo.messageDelResponse;

public class DeletePet {

    public static final Logger LOGGER = LoggerFactory.getLogger(DeletePet.class);

    @Test
    public static void deletePetTest() throws IOException {
        LOGGER.info("START TEST delete pet from the store");

        ResponseInfo response = PetClient.deletePetById(addingPet().getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), messageDelResponse().getType());
        Assert.assertEquals(response.getMessage(), messageDelResponse().getMessage());

        LOGGER.info("END TEST");
    }

}
