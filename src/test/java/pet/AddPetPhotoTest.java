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

public class AddPetPhotoTest extends AbstractPetTest {

    @BeforeMethod
    public void addPet() throws IOException {
        LOGGER.info("BEFORE TEST adding a pet");

        postPet(addingPet());

        LOGGER.info("BEFORE TEST pet added");
    }

    @Description("Verify that we can add a pet photo to the existing pet")
    @Test
    public void addPetPhotoTest() throws IOException {
        LOGGER.info("START TEST add pet photo to the existing pet ");

        ResponseInfo response = PetClient.postPetPicture(addingPet().getId());

        Assert.assertTrue(response.getMessage().contains("imp.png"));

        LOGGER.info("END TEST");
    }
}
