package petStoreTests.pet;

import client.PetClient;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static client.PetClient.deletePetById;
import static data.DataSet.addingPet;
import static data.ReusableMethods.getBigData;

public class AddTest {
    @Description("Verify that we are adding pet to the store")
    @Parameters({"Pet for adding"})
    @Test(dataProvider = "positiveTests")
    public void addPetTest(Pet simplePet) {
        Allure.step("Start test add pet to the store");
        Pet response = PetClient.postPet(simplePet);

        Assert.assertNotNull(response.getId());
        Assert.assertEquals(response.getName(), simplePet.getName());
        Assert.assertEquals(response.getStatus(), simplePet.getStatus());
        Assert.assertEquals(response.getCategory().getName(), simplePet.getCategory().getName());
        Assert.assertEquals(response.getCategory().getId(), simplePet.getCategory().getId());
        Assert.assertEquals(response.getPhotoUrls().toString(), simplePet.getPhotoUrls().toString());
        Assert.assertEquals(response.getTags(), simplePet.getTags());

        Allure.step("After test deleting pet");
        deletePetById(response.getId());
    }

    @DataProvider
    public Object[][] positiveTests() throws IOException {
        return new Object[][]{
            {addingPet()},
            {addingPet().setName(null)},
            {addingPet().setCategory(new PetCategory())},
            {addingPet().setPhotoUrls(Collections.emptyList())},
            {addingPet().setTags(new ArrayList<PetCategory>())},
            {addingPet().setTags(Collections.emptyList())},
            {addingPet().setStatus(null)},
            {addingPet().setId(0)},
            {addingPet().setId(-9223372036854775808L)},
            {addingPet().setId(9223372036854775807L)},
            {addingPet().setName(getBigData())},
            {addingPet().setCategory(new PetCategory().setName(getBigData()))},
            {addingPet().setTags(new ArrayList<PetCategory>()).setName(getBigData())},
            {addingPet().setStatus(getBigData())},
            {addingPet().setPhotoUrls(Collections.emptyList()).setName(getBigData())},
        };
    }
}
