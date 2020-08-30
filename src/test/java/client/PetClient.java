package client;

import data.Resources;
import dto.requests.EStatus;
import dto.requests.pet.Pet;
import dto.requests.pet.ResponseInfo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import pet.AbstractTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PetClient extends AbstractTest {

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
            .expectContentType(ContentType.JSON)
            .build();
    }

    public static ResponseSpecification buildUncheckedRes() {
        LOGGER.debug("building response specification ");

        return new ResponseSpecBuilder()
            .build();
    }

    public static Pet postPet(Pet petToPost) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq())
//            .log()
//            .all()
            .body(petToPost);

        LOGGER.debug("expecting response");

        return res
            .when()
            .post(Resources.postPet())
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(Pet.class);
    }

    public static Response getPetById(long petId) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .get(Resources.getPetById(petId))
            .then()
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response();
    }

    public static ResponseInfo deletePetById(long petId) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .delete(Resources.getPetById(petId))
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    public static Response deleteDefunctPetById(long petId) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .delete(Resources.getPetById(petId))
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response();
    }

    public static Response getPetByStatus(EStatus petStatus) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .get(Resources.getPetByStatus(petStatus.toString()))
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .extract()
            .response();
    }

}
