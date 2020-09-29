package petStoreTests.pet;

import client.PetClient;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static client.PetClient.*;
import static data.DataSet.addingPet;

public class DeleteNonExistingPetTest {
    @Description("Verify that we will get error trying delete defunct pet")
    @Test
    public void deleteDefunctPetTest() {
        Allure.step("Precondition deleting pet to insure that there won't be any pet with our petID");
        deleteNonExistingPetById(addingPet().getId());

        Allure.step("Start test try to delete defunct pet");
        Response response = PetClient.deleteNonExistingPetById(addingPet().getId());

        Assert.assertTrue(response.asString().trim().isEmpty());
    }
}
