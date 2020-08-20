package pet;

import data.Resources;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPetTest extends AbstractTest {

    @Test
    public void getPetTest() throws IOException {

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

        System.out.println();

        RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri(getBaseUrl("PET_STORE_HOST"))
            .setContentType(ContentType.JSON)
            .build();

        ResponseSpecification resSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

        RequestSpecification res = given()
            .spec(request)
            .log()
            .all()
            .body(newPet);

        Pet pet = res
            .when()
            .post(Resources.postPet())
            .then()
            .spec(resSpec)
            .log()
            .body()
            .body("status", equalTo("available"))
            .extract()
            .response()
            .as(Pet.class);

    }
}