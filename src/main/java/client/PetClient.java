package client;

import dto.requests.ResponseInfo;
import dto.requests.pet.Pet;
import enums.EStatus;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.File;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

public class PetClient extends BaseClient {
    final private static String PET_ENDPOINT = "/v2/pet/";
    final private static String PET_FIND_BY_STATUS_ENDPOINT = PET_ENDPOINT + "findByStatus?status=";
    final private static String PET_IMAGE_UPLOAD_ENDPOINT = PET_ENDPOINT + "%s/uploadImage";    // setting here pet ID


    @Step("Sending POST request to /pet")
    public static Pet postPet(Pet petToPost) {
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

    @Step("Sending GET request to /pet by ID {0}")
    public static Pet getPetById(long petId) {
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

    @Step("Sending GET request  for non existent pet to /pet by ID {0}")
    public static ResponseInfo getNonExistingPetById(long petId) {
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

    @Step("Sending DELETE request to /pet by ID {0}")
    public static ResponseInfo deletePetById(long petId) {
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

    @Step("Sending DELETE request for non existent order to /pet by ID {0}")
    public static Response deleteNonExistingPetById(long petId) {
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

    @Step("Sending GET request to /pet/findByStatus by pet status {0}")
    public static List<Pet> getPetByStatus(EStatus petStatus) {
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

    @Step("Sending POST request to /pet/petId/uploadImage by pet Id {0}")
    public static ResponseInfo postPetPicture(Long petId) {
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

    @Step("Sending PUT request to /pet")
    public static Pet putPet(Pet petToPut) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq())
            .body(petToPut);

        LOGGER.debug("expecting response");
        return res
            .when()
            .put(PET_ENDPOINT)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(Pet.class);
    }
}
