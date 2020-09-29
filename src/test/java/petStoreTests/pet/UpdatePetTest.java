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

import static client.PetClient.postPet;
import static data.DataSet.addingPet;
import static data.ReusableMethods.getBigData;

public class UpdatePetTest {
    @Description("Verify that we are updating pet info")
    @Parameters({"Pet for updating"})
    @Test(dataProvider = "positiveTests")
    public static void updatePetTest(Pet simplePet) {
        Allure.step("Before test adding pet");
        postPet(addingPet());

        Allure.step("Start test update pet information");
        Pet response = PetClient.putPet(simplePet);

        Assert.assertEquals(response.getName(), simplePet.getName());
        Assert.assertEquals(response.getStatus(), simplePet.getStatus());
        Assert.assertEquals(response.getCategory(), simplePet.getCategory());
        Assert.assertEquals(response.getPhotoUrls().toString(), simplePet.getPhotoUrls().toString());
        Assert.assertEquals(response.getTags(), simplePet.getTags());
    }

    @DataProvider
    public Object[][] positiveTests() throws IOException {
        return new Object[][]{
            {addingPet().setName(null)},
            {addingPet().setCategory(new PetCategory())},
            {addingPet().setPhotoUrls(Collections.emptyList())},
            {addingPet().setTags(Collections.emptyList())},
            {addingPet().setStatus(null)},
            {addingPet().setName(getBigData())},
            {addingPet().setCategory(new PetCategory().setName(getBigData()))},
            {addingPet().setTags(new ArrayList<PetCategory>()).setName(getBigData())},
            {addingPet().setStatus(getBigData())},
            {addingPet().setPhotoUrls(Collections.emptyList()).setName(getBigData())},
        };
    }
}
