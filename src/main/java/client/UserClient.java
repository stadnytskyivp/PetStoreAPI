package client;

import dto.requests.ResponseInfo;
import dto.requests.user.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.IOException;

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

}
