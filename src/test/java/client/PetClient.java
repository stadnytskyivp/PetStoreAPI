package client;

import data.Resources;
import dto.requests.pet.DeleteRes;
import dto.requests.pet.Pet;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(PetClient.class);

    public static String getBaseUrl(String hostName) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
            "\\src\\main\\resources\\env.properties");
        properties.load(fis);
        return properties.getProperty(hostName);
    }

    public static RequestSpecification buildReq() throws IOException {
        LOGGER.debug("building request specification ");

        return new RequestSpecBuilder()
            .setBaseUri(getBaseUrl("PET_STORE_HOST"))
            .setContentType(ContentType.JSON)
            .build();
    }

    public static ResponseSpecification buildRes() {
        LOGGER.debug("building response specification ");

        return new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
    }

    public static Pet postPet(Pet petToPost) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = given()
            .spec(buildReq())
//            .log()
//            .all()
            .body(petToPost);

        LOGGER.debug("expecting response");
        Pet pet = res
            .when()
            .post(Resources.postPet())
            .then()
            .spec(buildRes())
            .log()
            .body()
            .body("status", equalTo("available"))
            .extract()
            .response()
            .as(Pet.class);

        return pet;

    }

    public static Pet getPetById() throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        Pet pet = res
            .when()
            .get(Resources.getPetById("669118"))
            .then()
            .spec(buildRes())
            .log()
            .body()
            .body("status", equalTo("available"))
            .extract()
            .response()
            .as(Pet.class);

        return pet;

    }

    public static DeleteRes deletePetById() throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        DeleteRes pet = res
            .when()
            .delete(Resources.getPetById("669118"))
            .then()
            .spec(buildRes())
            .log()
            .body()
            .body("code", equalTo(200))
            .extract()
            .response()
            .as(DeleteRes.class);

        return pet;

    }


}
