package pet;

import client.PetClient;
import data.PetInfo;
import dto.requests.pet.Pet;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.addingPet;

public class FindPetTest extends AbstractTest {

    @Description("Verify that we are getting pets by id")
    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST find pet in the store");

        Pet pet = PetClient.getPetById(PetInfo.addingPet().getId());

        Assert.assertEquals(pet.getId(), addingPet().getId());
        Assert.assertEquals(pet.getName(), addingPet().getName());
        Assert.assertEquals(pet.getStatus(), addingPet().getStatus());
        Assert.assertEquals(pet.getCategory(), addingPet().getCategory());
        Assert.assertEquals(pet.getPhotoUrls().toString(), addingPet().getPhotoUrls().toString());
        Assert.assertEquals(pet.getTags().getClass(), pet.getTags().getClass());

        LOGGER.info("END TEST");
    }

}
