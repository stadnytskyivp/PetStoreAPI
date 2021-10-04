package client;

import data.ReusableMethods;
import dto.requests.ResponseInfo;
import dto.requests.store.Order;
import enums.ERequestType;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.Formatter;
import java.util.Objects;

public class StoreClient extends BaseClient {
    final private static String STORE_ORDER_ENDPOINT = "/v2/store/order";
    final private static String STORE_ORDER_BY_ID_ENDPOINT = STORE_ORDER_ENDPOINT + "/%s";
    final private static int THIRTY_SECONDS = 60;

    @Step("Sending POST request to /store/order")
    public static Order postOrder(Order orderToPost) {
        LOGGER.debug("sending request");
        RequestSpecification reqSpec = RestAssured.given()
                .spec(buildReq())
                .body(orderToPost);

        LOGGER.debug("checking response");
        Response response = verifyResponse(reqSpec, ERequestType.POST, STORE_ORDER_ENDPOINT, 0);

        LOGGER.debug("expecting response");
        return response
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
    public static Order getOrderById(long orderId) {
        LOGGER.debug("sending request");
        RequestSpecification reqSpec = RestAssured.given()
                .spec(buildReq());

        LOGGER.debug("checking response");
        Response response = verifyResponse(reqSpec, ERequestType.GET, STORE_ORDER_BY_ID_ENDPOINT, orderId);

        LOGGER.debug("expecting response");
        return response
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
    public static ResponseInfo deleteOrderById(long orderId) {
        LOGGER.debug("sending request");
        RequestSpecification reqSpec = RestAssured.given()
                .spec(buildReq())
                .when();

        LOGGER.debug("checking response");
        Response response = verifyResponse(reqSpec, ERequestType.DELETE, STORE_ORDER_BY_ID_ENDPOINT, orderId);

        LOGGER.debug("extracting response");
        return response
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
    public static ResponseInfo getNonexistentOrderById(long orderId) {
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
    public static ResponseInfo deleteNonExistingOrderById(long orderId) {
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

    private static Response verifyResponse(RequestSpecification reqSpec, ERequestType requestType, String endpoint, long orderId) {
        int actualStatusCode;
        int i = 0;
        String orderIdStr;

        if (orderId == 0) {
            orderIdStr = null;
        } else {
            orderIdStr = String.valueOf(orderId);
        }

        Response response = null;
        do {
            if (i == THIRTY_SECONDS) {
                break;
            }
            response = Objects.requireNonNull(requestType(requestType)).executeRequest(reqSpec, endpoint, orderIdStr);
            actualStatusCode = response.getStatusCode();
            ReusableMethods.waiter();
            i++;
        } while (HttpStatus.SC_OK != actualStatusCode);
        return response;
    }
}
