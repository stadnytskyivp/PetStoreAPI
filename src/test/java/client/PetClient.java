package client;

import data.Resources;
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
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
            .expectContentType(ContentType.JSON)
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
        Pet pet = res
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

        return pet;

    }

    public static Response getPetById(long petId, int statusCode) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        Response pet = res
            .when()
            .get(Resources.getPetById(petId))
            .then()
            .statusCode(statusCode)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response();
//            .as(Pet.class);

        return pet;

    }

    public static ResponseInfo deletePetById(long petId) throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        ResponseInfo pet = res
            .when()
            .delete(Resources.getPetById(petId))
            .then()
            .spec(buildRes())
            .log()
            .body()
            .body("code", Matchers.equalTo(HttpStatus.SC_OK))
            .extract()
            .response()
            .as(ResponseInfo.class);

        return pet;

    }


}
