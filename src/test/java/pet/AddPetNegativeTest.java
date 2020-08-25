package pet;

import client.PetClient;
import data.providers.AddPetData;
import dto.requests.pet.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddPetNegativeTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddPetNegativeTest.class);

    @Test(dataProvider = "negativePet", dataProviderClass = AddPetData.class)
    public void getPetTest(Pet simplePet) throws IOException {
        LOGGER.info("START TEST add pet to the store ");

        Pet response = PetClient.postPet(simplePet);

        Assert.assertEquals(response.getName(), simplePet.getName());
        Assert.assertEquals(response.getStatus(), simplePet.getStatus());
        Assert.assertEquals(response.getCategory().getName(), simplePet.getCategory().getName());
        Assert.assertEquals(response.getCategory().getId(), simplePet.getCategory().getId());

        LOGGER.info("END TEST");
    }
}