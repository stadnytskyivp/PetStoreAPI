package pet;

import client.PetClient;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AbstractTest {

    public static String getBaseUrl(String hostName) throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
            "\\src\\main\\resources\\env.properties");
        properties.load(fis);

        return properties.getProperty(hostName);
    }

    public static PetClient getPetClient() {
        System.out.println("   STEP 1   ");
        return new PetClient();
    }

    protected Pet postPet(){

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
