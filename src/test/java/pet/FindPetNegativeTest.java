package pet;

import client.PetClient;
import dto.requests.pet.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.postPet;

public class FindPetTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(FindPetTest.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST find pet in the store");

        Pet pet = PetClient.getPetById();

        Assert.assertEquals(pet.getId(), postPet().getId());
        Assert.assertEquals(pet.getName(), postPet().getName());
        Assert.assertEquals(pet.getStatus(), postPet().getStatus());
        Assert.assertEquals(pet.getCategory().getName(), postPet().getCategory().getName());
        Assert.assertEquals(pet.getCategory().getId(), postPet().getCategory().getId());
        Assert.assertEquals(pet.getPhotoUrls().toString(), postPet().getPhotoUrls().toString());
        Assert.assertEquals(pet.getTags().size(), postPet().getTags().size());

        LOGGER.info("END TEST");
    }

}
