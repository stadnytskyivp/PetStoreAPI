package data.providers;

import data.PetInfo;
import org.testng.annotations.DataProvider;

public class AddPetData {

    @DataProvider
    public Object[][] negativePet() {
        return new Object[][]{
            {PetInfo.emptyPetName()}, {PetInfo.emptyPetId()}, {PetInfo.emptyPetName()}
        };
    }

}
