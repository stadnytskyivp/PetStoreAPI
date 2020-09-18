package client;

import dto.requests.ResponseInfo;
import dto.requests.store.Order;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.Formatter;

public class StoreClient extends Client {

    final private static String STORE_ORDER_ENDPOINT = "/v2/store/order";
    final private static String STORE_ORDER_BY_ID_ENDPOINT = STORE_ORDER_ENDPOINT + "/%s";
    final private static String STORE_INVENTORY_ENDPOINT = "/v2/store/inventory";

    @Step("Adding order to the petStoreTests.store")
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

    @Step("Getting order by ID {0}")
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

    @Step("Deleting order by ID {0}")
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

    @Step("Getting order by ID {0}")
    public static Response getStoreInventory() throws IOException {

        LOGGER.debug("sending request");
        RequestSpecification res = RestAssured.given()
            .spec(buildReq());

        LOGGER.debug("expecting response");

        return res
            .when()
            .get(STORE_INVENTORY_ENDPOINT)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .spec(buildRes())
            .log()
            .body()
            .extract()
            .response();
    }

    @Step("Trying of getting nonexistent order by ID {0}")
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
}