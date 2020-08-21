package pet;

import data.Resources;
import dto.requests.pet.Pet;
import dto.requests.pet.PetCategory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FindPetTest extends AbstractTest {

    private final String EXPECTED_NAME = "Bilbo";
    private final String EXPECTED_STATUS = "available";
    private final PetCategory EXPECTED_PetCategory = new PetCategory();


    public static final Logger LOGGER = LoggerFactory.getLogger(FindPetTest.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST find pet in the store");

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
            .spec(request);

        LOGGER.debug("expecting response");
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

        Assert.assertEquals(pet.getName(),EXPECTED_NAME);
        Assert.assertEquals(pet.getStatus(),EXPECTED_STATUS);
        Assert.assertEquals(pet.getCategory().getName(),EXPECTED_PetCategory.getName());
        Assert.assertEquals(pet.getCategory().getId(),EXPECTED_PetCategory.getId());

        LOGGER.info("END TEST");
    }
}
