package pet;

import data.Resources;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeletePet extends AbstractTest {

    private final String EXPECTED_TYPE = "unknown";
    private final String EXPECTED_MESSAGE = "669118";

    public static final Logger LOGGER = LoggerFactory.getLogger(DeletePet.class);

    @Test
    public void getPetTest() throws IOException {
        LOGGER.info("START TEST delete pet from the store");

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
        String pet = res
            .when()
            .delete(Resources.getPetById("669118"))
            .then()
            .spec(resSpec)
            .log()
            .body()
            .body("code", equalTo(200))
            .extract()
            .response()
            .asString();

//        Assert.assertEquals(pet.getName(),EXPECTED_TYPE);
//        Assert.assertEquals(pet.getStatus(),EXPECTED_MESSAGE);

        LOGGER.info("END TEST");
    }

}
