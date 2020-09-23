package client;

import dto.requests.ResponseInfo;
import dto.requests.user.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.List;

public class UserClient extends BaseClient {
    final private static String USER_ENDPOINT = "/v2/user/";
    final private static String USER_LIST_ENDPOINT = USER_ENDPOINT + "createWithList";
    final private static String USER_ARRAY_ENDPOINT = USER_ENDPOINT + "createWithArray";

    @Step("Adding user to the store data base")
    public static ResponseInfo postUser(User userToPost) throws IOException {
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

    @Step("Getting user by username {0}")
    public static User getUserByUsername(String username) throws IOException {
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

    @Step("Deleting USER by username {0}")
    public static ResponseInfo deleteUserByUsername(String username) throws IOException {
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

    @Step("Deleting USER by username {0}")
    public static Response deleteNonExistingUser(String username) throws IOException {
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

    @Step("Adding user to the store data base")
    public static ResponseInfo updateUser(User userToPut) throws IOException {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
                .spec(buildReq())
                .body(userToPut);

        LOGGER.debug("expecting response");
        return res
                .when()
                .put(USER_ENDPOINT + userToPut.getUsername())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(buildRes())
                .log()
                .body()
                .extract()
                .response()
                .as(ResponseInfo.class);
    }

    @Step("Adding users to the store data base")
    public static ResponseInfo postUserList(List<User> usersToPost) throws IOException {
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

    @Step("Adding users to the store data base")
    public static ResponseInfo postUserArray(User[] usersToPost) throws IOException {
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
}
