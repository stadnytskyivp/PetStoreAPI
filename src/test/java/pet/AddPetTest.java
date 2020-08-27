package pet;

import client.PetClient;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static data.PetInfo.addingPet;
import static data.ReusableMethods.getBigData;

public class AddPetTest extends AbstractTest {

    @Test(dataProvider = "positiveTests")
    public static void addPetTest(Pet simplePet) throws IOException {
        LOGGER.info("START TEST add pet to the store ");

        Pet response = PetClient.postPet(simplePet);

        Assert.assertNotNull(response.getId());
        Assert.assertEquals(response.getName(), simplePet.getName());
        Assert.assertEquals(response.getStatus(), simplePet.getStatus());
        Assert.assertEquals(response.getCategory().getName(), simplePet.getCategory().getName());
        Assert.assertEquals(response.getCategory().getId(), simplePet.getCategory().getId());
        Assert.assertEquals(response.getPhotoUrls().toString(), simplePet.getPhotoUrls().toString());
        Assert.assertEquals(response.getTags().getClass(), simplePet.getTags().getClass());

        LOGGER.info("END TEST");
    }

    @DataProvider
    public Object[][] positiveTests() throws IOException {
        return new Object[][]{
            {addingPet()},
            {addingPet().setName(null)},
            {addingPet().setCategory(new PetCategory())},
            {addingPet().setPhotoUrls(Collections.emptyList())},
            {addingPet().setTags(new ArrayList<PetCategory>())},
            {addingPet().setTags(Collections.emptyList())},     // actual arrayList but expected emptyList
            {addingPet().setStatus(null)},
            {addingPet().setId(0)},
            {addingPet().setId(-9223372036854775808L)},
            {addingPet().setId(9223372036854775807L)},
            {addingPet().setName(getBigData())},
            {addingPet().setName("123")},
            {addingPet().setName("!@#$%^&")},
            {addingPet().setName("ЙЦУ йцу")},
            {addingPet().setName("♀♪♂")},
            {addingPet().setName("中华人民共和国")},
            {addingPet().setCategory(new PetCategory().setName(getBigData()))},
            {addingPet().setCategory(new PetCategory().setName("123"))},
            {addingPet().setCategory(new PetCategory().setName("!@#$%^&"))},
            {addingPet().setCategory(new PetCategory().setName("ЙЦУ йцу"))},
            {addingPet().setCategory(new PetCategory().setName("♀♪♂"))},
            {addingPet().setCategory(new PetCategory().setName("中华人民共和国"))},
            {addingPet().setTags(new ArrayList<PetCategory>()).setName(getBigData())},
            {addingPet().setTags(new ArrayList<PetCategory>()).setName("123")},
            {addingPet().setTags(new ArrayList<PetCategory>()).setName("!@#$%^&")},
            {addingPet().setTags(new ArrayList<PetCategory>()).setName("ЙЦУ йцу")},
            {addingPet().setTags(new ArrayList<PetCategory>()).setName("♀♪♂")},
            {addingPet().setTags(new ArrayList<PetCategory>()).setName("中华人民共和国")},
            {addingPet().setStatus(getBigData())},
            {addingPet().setStatus("123")},
            {addingPet().setStatus("!@#$%^&")},
            {addingPet().setStatus("ЙЦУ йцу")},
            {addingPet().setStatus("♀♪♂")},
            {addingPet().setStatus("中华人民共和国")},
            {addingPet().setPhotoUrls(Collections.emptyList()).setName(getBigData())},
            {addingPet().setPhotoUrls(Collections.emptyList()).setName("123")},
            {addingPet().setPhotoUrls(Collections.emptyList()).setName("!@#$%^&")},
            {addingPet().setPhotoUrls(Collections.emptyList()).setName("ЙЦУ йцу")},
            {addingPet().setPhotoUrls(Collections.emptyList()).setName("♀♪♂")},
            {addingPet().setPhotoUrls(Collections.emptyList()).setName("中华人民共和国")},
            // to be continued
        };
    }
}