package pet;

import client.PetClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.addingPet;

public class DeleteDefunctPetTest extends AbstractTest {

    @Test
    public void deleteDefunctPetTest() throws IOException {
        LOGGER.info("START TEST try to delete defunct pet");

        LOGGER.info("precondition adding and deleting pet to insure that there won't be any pet with our petID");
        AddPetTest.addPetTest(addingPet());
        DeletePetTest.deletePetTest();

        Response response = PetClient.deleteDefunctPetById(addingPet().getId());

        Assert.assertTrue(response.asString().trim().isEmpty());

        LOGGER.info("END TEST");
    }

}
