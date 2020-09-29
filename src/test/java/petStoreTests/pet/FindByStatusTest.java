package petStoreTests.pet;

import client.PetClient;
import dto.requests.pet.Pet;
import enums.EStatus;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class FindByStatusTest {
    @Story("Searching pets by status")
    @Description("Verify that we are getting pets by the right status")
    @Parameters({"Status for searching"})
    @Test(dataProvider = "statusData")
    public void getPetsByStatusTest(EStatus status) {
        Allure.step("Start test find pets in the store by status");
        List<Pet> pets = PetClient.getPetByStatus(status);

        for (Pet pet : pets) {
            String str = pet.getStatus();
            Assert.assertEquals(str, status.getStatus());
        }
    }

    @DataProvider
    public Object[][] statusData() {
        return new Object[][]{
            {EStatus.AVAILABLE},
            {EStatus.PENDING},
            {EStatus.SOLD},
        };
    }
}
