package client;

import data.DataSet;
import dto.requests.ResponseInfo;
import dto.requests.store.Order;
import dto.requests.user.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.Formatter;

public class UserClient extends BaseClient {
    final private static String USER_ENDPOINT = "/v2/user/";

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
}
