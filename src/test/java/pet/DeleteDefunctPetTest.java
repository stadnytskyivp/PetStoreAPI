package pet;

import client.PetClient;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static client.PetClient.deletePetById;
import static client.PetClient.postPet;
import static data.PetInfo.addingPet;

public class DeleteDefunctPetTest extends AbstractTest {

    @Description("Verify that we will get error trying delete defunct pet")
    @Test
    public void deleteDefunctPetTest() throws IOException {
        LOGGER.info("START TEST try to delete defunct pet");

        LOGGER.info("precondition adding and deleting pet to insure that there won't be any pet with our petID");
        postPet(addingPet());
        deletePetById(addingPet().getId());

        Response response = PetClient.deleteDefunctPetById(addingPet().getId());

        Assert.assertTrue(response.asString().trim().isEmpty());

        LOGGER.info("END TEST");
    }

}
