package petStoreTests.pet;

import client.PetClient;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static client.PetClient.deletePetById;
import static data.DataSet.addingPet;
import static data.ReusableMethods.getBigData;

public class AddTest extends AbstractTest {

    @Description("Verify that we are adding petStoreTests.pet to the petStoreTests.store")
    @Parameters({"Pet for adding"})
    @Test(dataProvider = "positiveTests")
    public void addPetTest(Pet simplePet) throws IOException {
        LOGGER.info("START TEST add petStoreTests.pet to the petStoreTests.store ");

        Pet response = PetClient.postPet(simplePet);

        Assert.assertNotNull(response.getId());
        Assert.assertEquals(response.getName(), simplePet.getName());
        Assert.assertEquals(response.getStatus(), simplePet.getStatus());
        Assert.assertEquals(response.getCategory().getName(), simplePet.getCategory().getName());
        Assert.assertEquals(response.getCategory().getId(), simplePet.getCategory().getId());
        Assert.assertEquals(response.getPhotoUrls().toString(), simplePet.getPhotoUrls().toString());
        Assert.assertEquals(response.getTags(), simplePet.getTags());

        LOGGER.info("END TEST");

        LOGGER.info("AFTER TEST DELETING PET");

        deletePetById(response.getId());

        LOGGER.info("AFTER TEST PET DELETED");
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