package client;

import enums.EStatus;
import dto.requests.pet.Pet;
import dto.requests.ResponseInfo;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PetClient extends Client {
    final private static String PET_ENDPOINT = "/v2/pet/";
    final private static String PET_FIND_BY_STATUS_ENDPOINT = PET_ENDPOINT + "findByStatus?status=";
    final private static String PET_IMAGE_UPLOAD_ENDPOINT = PET_ENDPOINT + "%s/uploadImage";    // setting here petStoreTests.pet ID


    @Step("Adding petStoreTests.pet to the petStoreTests.store")
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

    @Step("Getting petStoreTests.pet by ID {0}")
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

    @Step("Getting non existing petStoreTests.pet by ID {0}")
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

    @Step("Deleting petStoreTests.pet by ID {0}")
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

    @Step("Deleting defunct petStoreTests.pet by ID {0}")
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

    @Step("Adding petStoreTests.pet photo to the petStoreTests.pet")
    public static ResponseInfo postPetPicture(Long petId) throws IOException {

        LOGGER.debug("sending request");

        RequestSpecification res = RestAssured.given()
            .spec(buildUncheckedReq())
            .multiPart("file", new File(System.getProperty("petStoreTests.user.dir") +
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
