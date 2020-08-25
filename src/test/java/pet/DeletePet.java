package pet;

import client.PetClient;
import dto.requests.pet.DeleteRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeletePet {

    private final String EXPECTED_TYPE = "unknown";
    private final String EXPECTED_MESSAGE = "669118";

    public static final Logger LOGGER = LoggerFactory.getLogger(DeletePet.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST delete pet from the store");

        DeleteRes response = PetClient.deletePetById();

        Assert.assertEquals(response.getType(), EXPECTED_TYPE);
        Assert.assertEquals(response.getMessage(), EXPECTED_MESSAGE);

        LOGGER.info("END TEST");
    }

}
