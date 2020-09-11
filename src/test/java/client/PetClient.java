package client;

import data.Resources;
import dto.requests.EStatus;
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
import pet.AbstractTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PetClient extends AbstractTest {

    @Step("Getting base URL")
    public static String getBaseUrl(String hostName) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
            "/src/main/resources/env.properties");
        properties.load(fis);
        return properties.getProperty(hostName);
    }

    @Step("Building request specification")
    public static RequestSpecification buildReq() throws IOException {
        LOGGER.debug("building request specification ");

        return new RequestSpecBuilder()
            .setBaseUri(getBaseUrl("PET_STORE_HOST"))
            .setContentType(ContentType.JSON)
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

    @Step("Getting pet by ID {0}")
    public static Pet getPetById(long petId) throws IOException {

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
            .get(Resources.getPetById(petId))
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

    @Step("Deleting defunct pet by ID {0}")
    public static Response deleteNonExistingPetById(long petId) throws IOException {

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

    @Step("Getting list of pets with specific status {0}")
    public static List<Pet> getPetByStatus(EStatus petStatus) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return Arrays.asList(res
            .when()
            .get(Resources.getPetByStatus(petStatus.getStatus()))
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .extract()
            .response()
            .as(Pet[].class));
    }

}
