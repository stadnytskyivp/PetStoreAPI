package data.providers;

import data.PetInfo;
import org.testng.annotations.DataProvider;

public class AddPetData {

    @DataProvider
    public Object[][] negativePet() {
        return new Object[][]{
            {PetInfo.emptyPetName()},
            {PetInfo.emptyPetId()},
            {PetInfo.emptyPetCategory()},
            {PetInfo.emptyPetPhotoUrls()},
            {PetInfo.emptyPetTags()},
            {PetInfo.emptyPetStatus()}
        };
    }

}
