package data;

import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import dto.requests.ResponseInfo;
import dto.requests.store.Order;
import dto.requests.user.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataSet {

    public static Pet addingPet() {
        PetCategory petCategory = new PetCategory().setId(666).setName("Imp");

        List<String> photoUrls = Collections.singletonList("https://vignette.wikia.nocookie.net/disciples-world/images/3/33/Imp.jpg/revision/" +
            "latest?cb=20200125135519&path-prefix=ru");
        List<PetCategory> tags = Arrays.asList(petCategory, petCategory);

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

    public static Order addingOrder() {
        return new Order()
            .setId(1992)
            .setPetId(669118L)
            .setQuantity(1)
            .setShipDate("2020-09-16T12:31:21.005+0000")
            .setStatus("placed")
            .setComplete(true);
    }

    public static User addingUser() {
        return new User()
            .setId(311)
            .setUsername("Potato")
            .setFirstName("Carl")
            .setLastName("Jonson")
            .setEmail("CJ@gmail.com")
            .setPassword("123456")
            .setPhone("+380931488666")
            .setUserStatus(1);
    }
}
