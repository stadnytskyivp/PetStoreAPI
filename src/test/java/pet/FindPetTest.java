package pet;

import data.Resources;
import dto.requests.pet.Pet;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FindPetTest extends AbstractTest {

    @Test
    public void getPetTest() throws IOException {

        RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri(getBaseUrl("PET_STORE_HOST"))
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
            .log()
            .body()
            .body("status", equalTo("available"))
            .extract()
            .response()
            .as(Pet.class);

    }
}
