package pet;

import client.PetClient;
import dto.requests.pet.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.postPet;

public class AddPetTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddPetTest.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST add pet to the store ");

        Pet response = PetClient.postPet(postPet());

        Assert.assertEquals(response.getName(), postPet().getName());
        Assert.assertEquals(response.getStatus(), postPet().getStatus());
        Assert.assertEquals(response.getCategory().getName(), postPet().getCategory().getName());
        Assert.assertEquals(response.getCategory().getId(), postPet().getCategory().getId());

        LOGGER.info("END TEST");
    }
}