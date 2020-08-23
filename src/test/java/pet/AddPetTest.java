package pet;

import client.PetClient;
import data.InputInfo;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddPetTest {

    private static final String EXPECTED_NAME = "Bilbo";
    private static final String EXPECTED_STATUS = "available";
    private static PetCategory EXPECTED_PetCategory = new PetCategory();

    public static final Logger LOGGER = LoggerFactory.getLogger(AddPetTest.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST add pet to the store ");

        EXPECTED_PetCategory.setId(666);
        EXPECTED_PetCategory.setName("Imp");

        Pet response = PetClient.postPet(InputInfo.postPet());

        Assert.assertEquals(response.getName(),EXPECTED_NAME);
        Assert.assertEquals(response.getStatus(),EXPECTED_STATUS);
        Assert.assertEquals(response.getCategory().getName(),EXPECTED_PetCategory.getName());
        Assert.assertEquals(response.getCategory().getId(),EXPECTED_PetCategory.getId());
//
        LOGGER.info("END TEST");
    }
}