package pet;

import data.Resources;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPetTest extends AbstractTest {

    private static final String EXPECTED_NAME = "Bilbo";
    private static final String EXPECTED_STATUS = "available";
    private static PetCategory EXPECTED_PetCategory = new PetCategory();

    public static final Logger LOGGER = LoggerFactory.getLogger(AddPetTest.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST add pet to the store ");

        EXPECTED_PetCategory.setId(666);
        EXPECTED_PetCategory.setName("Imp");

        LOGGER.debug("building request specification ");
        RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri(getBaseUrl("PET_STORE_HOST"))
            .setContentType(ContentType.JSON)
            .build();

        LOGGER.debug("building response specification ");
        ResponseSpecification resSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

        LOGGER.debug("sending request");
        RequestSpecification res = given()
            .spec(request)
//            .log()
//            .all()
            .body(postPet());

        LOGGER.debug("expecting response");
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

        Assert.assertEquals(pet.getName(),EXPECTED_NAME);
        Assert.assertEquals(pet.getStatus(),EXPECTED_STATUS);
        Assert.assertEquals(pet.getCategory().getName(),EXPECTED_PetCategory.getName());
        Assert.assertEquals(pet.getCategory().getId(),EXPECTED_PetCategory.getId());

        LOGGER.info("END TEST");
    }
}