package pet;

import client.PetClient;
import data.PetInfo;
import dto.requests.pet.Pet;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.addingPet;

public class FindPetTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(FindPetTest.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST find pet in the store");

        Response res = PetClient.getPetById(PetInfo.addingPet().getId());
        Pet pet = res.as(Pet.class);

        Assert.assertEquals(pet.getId(), addingPet().getId());
        Assert.assertEquals(pet.getName(), addingPet().getName());
        Assert.assertEquals(pet.getStatus(), addingPet().getStatus());
        Assert.assertEquals(pet.getCategory().getName(), addingPet().getCategory().getName());
        Assert.assertEquals(pet.getCategory().getId(), addingPet().getCategory().getId());
        Assert.assertEquals(pet.getPhotoUrls().toString(), addingPet().getPhotoUrls().toString());
        Assert.assertEquals(pet.getTags().size(), addingPet().getTags().size());

        LOGGER.info("END TEST");
    }

}
