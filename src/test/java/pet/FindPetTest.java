package pet;

import data.Resources;
import dto.responses.pet.Pet;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FindPetTest {

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

        RequestSpecification request = new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("PET_STORE_HOST"))
                .setContentType(ContentType.JSON)
                .build();

        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        RequestSpecification res = given()
                .spec(request);

        Pet pet = res
                .when()
                .get(Resources.getPetById("669118"))
                .then()
                .spec(resSpec)
                .body("status", equalTo("available"))
                .extract()
                .response()
                .as(Pet.class);

        System.out.println("\nGet pet : ");
        System.out.println(pet.getId());
        System.out.println(pet.getName());
        System.out.println(pet.getPhotoUrls());
        System.out.println(pet.getStatus());
        System.out.println(pet.getCategory());
        System.out.println(pet.getTags());

    }
}
