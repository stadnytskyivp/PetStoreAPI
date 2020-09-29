package petStoreTests.pet;

import client.PetClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static client.PetClient.postPet;
import static data.DataSet.addingPet;
import static data.DataSet.messageUnknownResponse;

public class DeleteTest {
    @Description("Verify that we are deleting pet")
    @Test
    public static void deletePetTest() {
        Allure.step("Before test adding pet");
        postPet(addingPet());

        Allure.step("Start test delete pet from the store");
        ResponseInfo response = PetClient.deletePetById(addingPet().getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), messageUnknownResponse().getType());
        Assert.assertEquals(response.getMessage(), messageUnknownResponse().getMessage());
    }
}
