package client;

import dto.requests.pet.Pet;
import dto.requests.store.Order;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.IOException;

public class StoreClient extends Client {

    final private static String STORE_ORDER_ENDPOINT = "/store/order";
    final private static String STORE_ORDER_BY_ID_ENDPOINT = STORE_ORDER_ENDPOINT + "/%s";
    final private static String STORE_INVENTORY_ENDPOINT = "/store/inventory";

    @Step("Adding order to the store")
    public static Pet postOrder(Order orderToPost) throws IOException {

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
            .as(Pet.class);
    }
}