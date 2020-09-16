package pet;

import client.PetClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static client.PetClient.postPet;
import static data.DataSet.addingPet;
import static data.DataSet.messageDelResponse;

public class DeletePetTest extends AbstractPetTest {

    @BeforeMethod
    public void removeAddedPet() throws IOException {
        LOGGER.info("BEFORE TEST adding a pet");

        postPet(addingPet());

        LOGGER.info("BEFORE TEST pet is added");
    }

    @Description("Verify that we are deleting pet")
    @Test
    public static void deletePetTest() throws IOException {
        LOGGER.info("START TEST delete pet from the store");

        ResponseInfo response = PetClient.deletePetById(addingPet().getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), messageDelResponse().getType());
        Assert.assertEquals(response.getMessage(), messageDelResponse().getMessage());

        LOGGER.info("END TEST");
    }

}
