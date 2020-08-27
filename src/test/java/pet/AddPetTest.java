package pet;

import client.PetClient;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collections;

import static data.PetInfo.addingPet;

public class AddPetTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddPetTest.class);

    @Test(dataProvider = "positiveTests")
    public static void addPetTest(Pet simplePet) throws IOException {
        LOGGER.info("START TEST add pet to the store ");

        Pet response = PetClient.postPet(simplePet);

        Assert.assertNotNull(response.getId());
        Assert.assertEquals(response.getName(), simplePet.getName());
        Assert.assertEquals(response.getStatus(), simplePet.getStatus());
        Assert.assertEquals(response.getCategory().getName(), simplePet.getCategory().getName());
        Assert.assertEquals(response.getCategory().getId(), simplePet.getCategory().getId());
        Assert.assertEquals(response.getPhotoUrls().toString(), simplePet.getPhotoUrls().toString());
        Assert.assertEquals(response.getTags().size(), simplePet.getTags().size());

        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] positiveTests() {
        return new Object[][]{
            {addingPet()},
            {addingPet().setName(null)},
            {addingPet().setCategory(new PetCategory())},
            {addingPet().setPhotoUrls(Collections.emptyList())},
            {addingPet().setTags(Collections.emptyList())},
            {addingPet().setStatus(null)},
            {addingPet().setId(0)},
            {addingPet().setId(-9223372036854775808L)},
            {addingPet().setId(9223372036854775807L)},
            // to be continued
        };
    }
}