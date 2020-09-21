package petStoreTests.pet;

import client.PetClient;
import enums.EStatus;
import dto.requests.pet.Pet;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import petStoreTests.AbstractTest;

import java.io.IOException;
import java.util.List;

public class FindByStatusTest extends AbstractTest {

    @Story("Searching pets by status")
    @Description("Verify that we are getting pets by the right status")
    @Parameters({"Status for searching"})
    @Test(dataProvider = "statusData")
    public void getPetsByStatusTest(EStatus status) throws IOException {
        LOGGER.info("START TEST find pets in the petStoreTests.store by status");
        List<Pet> pets = PetClient.getPetByStatus(status);

        for (Pet pet : pets) {
                String str = pet.getStatus();
                Assert.assertEquals(str, status.getStatus());
        }
        LOGGER.info("END TEST");
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