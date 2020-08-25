package data;

import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;

import java.util.ArrayList;
import java.util.List;

public class PetInfo {

    private static Pet petCreator(int petId, PetCategory petCategory, String petName, List<String> photoUrls,
                                  List<PetCategory> tags, String status) {

        Pet newPet = new Pet();
        newPet.setId(petId)
            .setCategory(petCategory)
            .setName(petName)
            .setPhotoUrls(photoUrls)
            .setTags(tags)
            .setStatus(status);

        return newPet;
    }


    public static Pet postPet() {
        PetCategory petCategory = new PetCategory();
        petCategory.setId(666);
        petCategory.setName("Imp");

        List<String> photoUrls = new ArrayList();
        photoUrls.add("https://vignette.wikia.nocookie.net/disciples-world/images/3/33/Imp.jpg/revision/" +
            "latest?cb=20200125135519&path-prefix=ru");

        List<PetCategory> tags = new ArrayList();
        tags.add(petCategory);

        Pet newPet = petCreator(669118, petCategory, "Bilbo", photoUrls, tags, "available");

        return newPet;
    }


    public static Pet emptyPetName() {
        PetCategory petCategory = new PetCategory();
        petCategory.setId(666);
        petCategory.setName("Imp");

        List<String> photoUrls = new ArrayList();
        photoUrls.add("https://vignette.wikia.nocookie.net/disciples-world/images/3/33/Imp.jpg/revision/" +
            "latest?cb=20200125135519&path-prefix=ru");

        List<PetCategory> tags = new ArrayList();
        tags.add(petCategory);

        Pet newPet = petCreator(669119, petCategory, "", photoUrls, tags, "available");

        return newPet;
    }

    public static Pet emptyPetId() {
        PetCategory petCategory = new PetCategory();
        petCategory.setId(666);
        petCategory.setName("Imp");

        List<String> photoUrls = new ArrayList();
        photoUrls.add("https://vignette.wikia.nocookie.net/disciples-world/images/3/33/Imp.jpg/revision/" +
            "latest?cb=20200125135519&path-prefix=ru");

        List<PetCategory> tags = new ArrayList();
        tags.add(petCategory);

        Pet newPet = petCreator(0, petCategory, "Bilbo", photoUrls, tags, "available");

        return newPet;
    }


}
