package petStoreTests.pet;

import client.PetClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;

import static client.PetClient.postPet;
import static data.DataSet.addingPet;
import static data.DataSet.messageUnknownResponse;

public class DeleteTest extends AbstractTest {
    @Description("Verify that we are deleting petStoreTests.pet")
    @Test
    public static void deletePetTest() throws IOException {
        LOGGER.info("BEFORE TEST adding a petStoreTests.pet");
        postPet(addingPet());
        LOGGER.info("BEFORE TEST petStoreTests.pet added");

        LOGGER.info("START TEST delete petStoreTests.pet from the petStoreTests.store");
        ResponseInfo response = PetClient.deletePetById(addingPet().getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), messageUnknownResponse().getMessage());
        LOGGER.info("END TEST");
    }
}
