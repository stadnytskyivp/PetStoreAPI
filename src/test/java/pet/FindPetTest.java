package pet;

import client.PetClient;
import data.DataSet;
import dto.requests.pet.Pet;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static client.PetClient.postPet;
import static data.DataSet.addingPet;

public class FindPetTest extends AbstractPetTest {

    @BeforeMethod
    public void removeAddedPet() throws IOException {
        LOGGER.info("BEFORE TEST adding a pet");

        postPet(addingPet());

        LOGGER.info("BEFORE TEST pet is added");
    }

    @Description("Verify that we are getting pets by id")
    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST find pet in the store");

        Pet pet = PetClient.getPetById(DataSet.addingPet().getId());

        Assert.assertEquals(pet.getId(), addingPet().getId());
        Assert.assertEquals(pet.getName(), addingPet().getName());
        Assert.assertEquals(pet.getStatus(), addingPet().getStatus());
        Assert.assertEquals(pet.getCategory(), addingPet().getCategory());
        Assert.assertEquals(pet.getPhotoUrls().toString(), addingPet().getPhotoUrls().toString());
        Assert.assertEquals(pet.getTags().getClass(), pet.getTags().getClass());

        LOGGER.info("END TEST");
    }

}
