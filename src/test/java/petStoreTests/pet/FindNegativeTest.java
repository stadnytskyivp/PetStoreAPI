package petStoreTests.pet;

import client.PetClient;
import dto.requests.ResponseInfo;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static client.PetClient.deletePetById;
import static client.PetClient.postPet;
import static data.DataSet.addingPet;
import static data.DataSet.messageNotFoundResponse;

public class FindNegativeTest {
    @Description("Verify that we will get error message by getting defunct pet by id")
    @Test
    public void getDefunctPetTest() {
        Allure.step("Precondition adding and deleting pet to insure that there won't be any pet with our petID");
        postPet(addingPet());
        deletePetById(addingPet().getId());

        Allure.step("Start test find pet in the store");
        ResponseInfo res = PetClient.getNonExistingPetById(addingPet().getId());

        Assert.assertEquals(res.getCode(), messageNotFoundResponse().getCode());
        Assert.assertEquals(res.getType(), messageNotFoundResponse().getType());
        Assert.assertEquals(res.getMessage(), messageNotFoundResponse().getMessage());
    }
}
