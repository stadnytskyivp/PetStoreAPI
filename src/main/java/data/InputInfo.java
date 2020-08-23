package data;

import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;

import java.util.ArrayList;
import java.util.List;

public class InputInfo {

    public static Pet postPet(){

        PetCategory petCategory = new PetCategory();
        petCategory.setId(666);
        petCategory.setName("Imp");

        List<String> photoUrls = new ArrayList();
        photoUrls.add("https://vignette.wikia.nocookie.net/disciples-world/images/3/33/Imp.jpg/revision/" +
            "latest?cb=20200125135519&path-prefix=ru");

        List<PetCategory> tags = new ArrayList();
        tags.add(petCategory);

        Pet newPet = new Pet();
        newPet.setId(669118)
            .setCategory(petCategory)
            .setName("Bilbo")
            .setPhotoUrls(photoUrls)
            .setTags(tags)
            .setStatus("available");

        return newPet;

    }

}
