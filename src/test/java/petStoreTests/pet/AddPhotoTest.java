package petStoreTests.pet;

import client.PetClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static client.PetClient.postPet;
import static data.DataSet.addingPet;

public class AddPhotoTest {
    @Description("Verify that we can add a pet photo to the existing pet")
    @Test
    public void addPetPhotoTest() {
        Allure.step("Before test adding a pet");
        postPet(addingPet());

        Allure.step("Start test add pet photo to the existing pet");
        ResponseInfo response = PetClient.postPetPicture(addingPet().getId());

        Assert.assertTrue(response.getMessage().contains("imp.png"));
    }
}
