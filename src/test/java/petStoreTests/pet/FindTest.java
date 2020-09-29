package petStoreTests.pet;

import client.PetClient;
import data.DataSet;
import dto.requests.pet.Pet;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static client.PetClient.postPet;
import static data.DataSet.addingPet;

public class FindTest {
    @Description("Verify that we are getting pets by id")
    @Test
    public void getPetTest() {
        Allure.step("Before test adding pet");
        postPet(addingPet());

        Allure.step("Start test find pet in the store");
        Pet pet = PetClient.getPetById(DataSet.addingPet().getId());

        Assert.assertEquals(pet.getId(), addingPet().getId());
        Assert.assertEquals(pet.getName(), addingPet().getName());
        Assert.assertEquals(pet.getStatus(), addingPet().getStatus());
        Assert.assertEquals(pet.getCategory(), addingPet().getCategory());
        Assert.assertEquals(pet.getPhotoUrls().toString(), addingPet().getPhotoUrls().toString());
        Assert.assertEquals(pet.getTags().getClass(), pet.getTags().getClass());
    }
}
