package client;

import dto.requests.ResponseInfo;
import dto.requests.user.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.List;

public class UserClient extends BaseClient {
    final private static String USER_ENDPOINT = "/v2/user/";
    final private static String USER_LIST_ENDPOINT = USER_ENDPOINT + "createWithList";
    final private static String USER_ARRAY_ENDPOINT = USER_ENDPOINT + "createWithArray";

    @Step("Sending POST request to /user")
    public static ResponseInfo postUser(User userToPost) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq())
            .body(userToPost);

        LOGGER.debug("expecting response");
        return res
            .when()
            .post(USER_ENDPOINT)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Sending GET request to /user with username {0}")
    public static User getUserByUsername(String username) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .get(USER_ENDPOINT + username)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(User.class);
    }

    @Step("Sending DELETE request to /user with username {0}")
    public static ResponseInfo deleteUserByUsername(String username) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .delete(USER_ENDPOINT + username)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Sending DELETE request for non existent user to /user with username {0}")
    public static Response deleteNonExistingUser(String username) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .delete(USER_ENDPOINT + username)
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response();
    }

    @Step("Sending PUT request to /user with username {0} for updating information")
    public static ResponseInfo updateUser(String oldUsername, User userToPut) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq())
            .body(userToPut);

        LOGGER.debug("expecting response");
        return res
            .when()
            .put(USER_ENDPOINT + oldUsername)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Sending POST request for creating list of users to /user/createWithList")
    public static ResponseInfo postUserList(List<User> usersToPost) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq())
            .body(usersToPost);

        LOGGER.debug("expecting response");
        return res
            .when()
            .post(USER_LIST_ENDPOINT)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Sending POST request for creating an array of users to /user/createWithArray")
    public static ResponseInfo postUserArray(User[] usersToPost) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq())
            .body(usersToPost);

        LOGGER.debug("expecting response");
        return res
            .when()
            .post(USER_ARRAY_ENDPOINT)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Sending DELETE request without any checks to /user with username {0} if they exist")
    public static Response deleteNonCheckedUser(String username) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildUncheckedReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .delete(USER_ENDPOINT + username)
            .then()
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response();
    }

    @Step("Sending GET request for trying getting non existing user to /user with username {0}")
    public static ResponseInfo getNonExistingUser(String username) {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildUncheckedReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .get(USER_ENDPOINT + username)
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }
}
