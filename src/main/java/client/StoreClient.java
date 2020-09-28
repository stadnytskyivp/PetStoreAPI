package client;

import dto.requests.ResponseInfo;
import dto.requests.store.Order;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.Formatter;

public class StoreClient extends BaseClient {
    final private static String STORE_ORDER_ENDPOINT = "/v2/store/order";
    final private static String STORE_ORDER_BY_ID_ENDPOINT = STORE_ORDER_ENDPOINT + "/%s";

    @Step("Sending POST request to /store/order")
    public static Order postOrder(Order orderToPost) throws IOException {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq())
            .body(orderToPost);

        LOGGER.debug("expecting response");
        return res
            .when()
            .post(STORE_ORDER_ENDPOINT)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(Order.class);
    }

    @Step("Sending GET request to /store/order by ID {0}")
    public static Order getOrderById(long orderId) throws IOException {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .get(String.valueOf(new Formatter().format(STORE_ORDER_BY_ID_ENDPOINT, orderId)))
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(Order.class);
    }

    @Step("Sending DELETE request to /store/order by ID {0}")
    public static ResponseInfo deleteOrderById(long orderId) throws IOException {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .delete(String.valueOf(new Formatter().format(STORE_ORDER_BY_ID_ENDPOINT, orderId)))
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildUncheckedRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Sending GET request for non existent order to /store/order by ID {0}")
    public static ResponseInfo getNonexistentOrderById(long orderId) throws IOException {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .get(String.valueOf(new Formatter().format(STORE_ORDER_BY_ID_ENDPOINT, orderId)))
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response()
            .as(ResponseInfo.class);
    }

    @Step("Sending DELETE request for non existent order to /store/order by ID {0}")
    public static ResponseInfo deleteNonExistingOrderById(long orderId) throws IOException {
        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");
        return res
            .when()
            .delete(String.valueOf(new Formatter().format(STORE_ORDER_BY_ID_ENDPOINT, orderId)))
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
