package data;

import dto.requests.pet.ResponseInfo;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;

import java.util.ArrayList;
import java.util.List;

public class PetInfo {

    public static Pet addingPet() {
        PetCategory petCategory = new PetCategory().setId(666).setName("Imp");

        List<String> photoUrls = new ArrayList();
        photoUrls.add("https://vignette.wikia.nocookie.net/disciples-world/images/3/33/Imp.jpg/revision/" +
            "latest?cb=20200125135519&path-prefix=ru");

        List<PetCategory> tags = new ArrayList();
        tags.add(petCategory);

        return new Pet().setId(669118L)
            .setCategory(petCategory)
            .setName("Bilbo")
            .setPhotoUrls(photoUrls)
            .setTags(tags)
            .setStatus("available");
    }

    public static ResponseInfo messageDelResponse() {

        return new ResponseInfo()
            .setCode(200)
            .setType("unknown")
            .setMessage("669118");
    }

    public static ResponseInfo messageNotFoundResponse() {

        return new ResponseInfo()
            .setCode(1)
            .setType("error")
            .setMessage("Pet not found");
    }

}
