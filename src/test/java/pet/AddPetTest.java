package pet;

import data.PayLoad;
import data.Resources;
import dto.responses.pet.Pet;
import dto.responses.pet.PetCategory;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPetTest {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\env.properties");
        properties.load(fis);
    }

    @Test
    public void getPetTest() {
        RestAssured.baseURI = properties.getProperty("PET_STORE_HOST");

        PetCategory petCategory = new PetCategory();
        petCategory.setId(666);
        petCategory.setName("Imp");

        List<String> photoUrls = new ArrayList();
        photoUrls.add("https://vignette.wikia.nocookie.net/disciples-world/images/3/33/Imp.jpg/revision/" +
                "latest?cb=20200125135519&path-prefix=ru");

        List<PetCategory> tags = new ArrayList();
        tags.add(petCategory);

        Pet newPet = new Pet();
        newPet.setId(669118);
        newPet.setCategory(petCategory);
        newPet.setName("Bilbo");
        newPet.setPhotoUrls(photoUrls);
        newPet.setTags(tags);
        newPet.setStatus("available");


        RequestSpecification request = new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("PET_STORE_HOST"))
                .setContentType(ContentType.JSON)
                .build();

        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        RequestSpecification res = given()
                .spec(request)
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