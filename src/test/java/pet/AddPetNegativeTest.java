package pet;

import client.PetClient;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static data.PetInfo.addingPet;

public class AddPetNegativeTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddPetNegativeTest.class);

    @Test(dataProvider = "negativePet")
    public void getPetTest(Pet simplePet) throws IOException {
        LOGGER.info("START TEST add pet to the store ");

        Pet response = PetClient.postPet(simplePet);

        Assert.assertEquals(response.getName(), simplePet.getName());
        Assert.assertEquals(response.getStatus(), simplePet.getStatus());
        Assert.assertEquals(response.getCategory().getName(), simplePet.getCategory().getName());
        Assert.assertEquals(response.getCategory().getId(), simplePet.getCategory().getId());
        Assert.assertEquals(response.getPhotoUrls().toString(), simplePet.getPhotoUrls().toString());
        Assert.assertEquals(response.getTags().size(), simplePet.getTags().size());

        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] negativePet() {
        return new Object[][]{
            {addingPet().setName("ABC abc 123 !@# ЙЦУ йцу ♀♪♂ 中华人民共和国")},
            {addingPet().setCategory(new PetCategory().setName("ABC abc 123 !@# ЙЦУ йцу ♀♪♂ 中华人民共和国"))},
            // to be continued
        };
    }

}