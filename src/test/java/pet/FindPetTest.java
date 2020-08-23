package pet;

import client.PetClient;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FindPetTest {

    private final String EXPECTED_NAME = "Bilbo";
    private final String EXPECTED_STATUS = "available";
    private final PetCategory EXPECTED_PetCategory = new PetCategory();


    public static final Logger LOGGER = LoggerFactory.getLogger(FindPetTest.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST find pet in the store");

        EXPECTED_PetCategory.setId(666);
        EXPECTED_PetCategory.setName("Imp");

        Pet pet = PetClient.getPetById();

        Assert.assertEquals(pet.getName(),EXPECTED_NAME);
        Assert.assertEquals(pet.getStatus(),EXPECTED_STATUS);
        Assert.assertEquals(pet.getCategory().getName(),EXPECTED_PetCategory.getName());
        Assert.assertEquals(pet.getCategory().getId(),EXPECTED_PetCategory.getId());

        LOGGER.info("END TEST");
    }
}
