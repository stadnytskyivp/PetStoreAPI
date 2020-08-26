package pet;

import client.PetClient;
import dto.requests.pet.Pet;
import dto.requests.pet.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.messageNotFoundResponse;

public class AddPetNegativeTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddPetNegativeTest.class);

    @Test(dataProvider = "negativePet")
    public void getPetTest(Pet simplePet) throws IOException {
        LOGGER.info("START TEST add pet to the store ");

        ResponseInfo response = PetClient.postNegativePet(simplePet);

        Assert.assertEquals(response.getCode(), messageNotFoundResponse().getCode());
        Assert.assertEquals(response.getType(), messageNotFoundResponse().getType());
        Assert.assertEquals(response.getMessage(), messageNotFoundResponse().getMessage());


        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] negativePet() {
        return new Object[][]{
//            {PetInfo.addingPet().setId(9223372036854775808L)},
//            {PetInfo.addingPet().setName("")},
            // to be continued
        };
    }

}