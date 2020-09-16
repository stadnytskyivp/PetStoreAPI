package client;

import enums.EStatus;
import dto.requests.pet.Pet;
import dto.requests.pet.ResponseInfo;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PetClient {
    final private static String BASE_URI = "https://petstore.swagger.io";
    final private static String PET_ENDPOINT = "/v2/pet/";
    final private static String PET_FIND_BY_STATUS_ENDPOINT = PET_ENDPOINT + "findByStatus?status=";
    final private static String PET_IMAGE_UPLOAD_ENDPOINT = PET_ENDPOINT + "%s/uploadImage";    // setting here pet ID

    public static final Logger LOGGER = LoggerFactory.getLogger(PetClient.class);

    @Step("Building request specification")
    public static RequestSpecification buildReq() throws IOException {
        LOGGER.debug("building request specification ");

        return new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build();
    }

    @Step("Building request specification")
    public static RequestSpecification buildUncheckedReq() throws IOException {
        LOGGER.debug("building request specification ");

        return new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .build();
    }

    @Step("Building response specification")
    public static ResponseSpecification buildRes() {
        LOGGER.debug("building response specification ");

        return new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .build();
    }

    @Step("Building response specification without any response checking")
    public static ResponseSpecification buildUncheckedRes() {
        LOGGER.debug("building response specification ");

        return new ResponseSpecBuilder()
            .build();
    }

    @Step("Adding pet to the store")
    public static Pet postPet(Pet petToPost) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq())
            .body(petToPost);

        LOGGER.debug("expecting response");

        return res
            .when()
            .post(PET_ENDPOINT)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(Pet.class);
    }

    @Step("Getting pet by ID {0}")
    public static Pet getPetById(long petId) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .get(PET_ENDPOINT + petId)
            .then()
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(Pet.class);
    }

    @Step("Getting non existing pet by ID {0}")
    public static ResponseInfo getNonExistingPetById(long petId) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .get(PET_ENDPOINT + petId)
            .then()
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Deleting pet by ID {0}")
    public static ResponseInfo deletePetById(long petId) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .delete(PET_ENDPOINT + petId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Deleting defunct pet by ID {0}")
    public static Response deleteNonExistingPetById(long petId) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .delete(PET_ENDPOINT + petId)
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response();
    }

    @Step("Getting list of pets with specific status {0}")
    public static List<Pet> getPetByStatus(EStatus petStatus) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return Arrays.asList(res
            .when()
            .get(PET_FIND_BY_STATUS_ENDPOINT + petStatus.getStatus())
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .extract()
            .response()
            .as(Pet[].class));
    }

    @Step("Adding pet photo to the pet")
    public static ResponseInfo postPetPicture(Long petId) throws IOException {

        LOGGER.debug("sending request");

        RequestSpecification res = RestAssured.given()
            .spec(buildUncheckedReq())
            .multiPart("file", new File(System.getProperty("user.dir") +
                "/img/imp.png"));

        LOGGER.debug("expecting response");

        return res
            .when()
            .post(String.valueOf(new Formatter().format(PET_IMAGE_UPLOAD_ENDPOINT, petId)))
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }
}
