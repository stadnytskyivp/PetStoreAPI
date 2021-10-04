package client;

import enums.ERequestType;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Formatter;

public class BaseClient {
    final private static String BASE_URI = "https://petstore.swagger.io";
    protected static final Logger LOGGER = LoggerFactory.getLogger(PetClient.class);

    @Step("Building request specification")
    public static RequestSpecification buildReq() {
        LOGGER.debug("building request specification ");
        return new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build();
    }

    @Step("Building request specification without any response checking")
    public static RequestSpecification buildUncheckedReq() {
        LOGGER.debug("building request specification ");
        return new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
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

    protected static RequestExecutor requestType(ERequestType requestType) {
        switch (requestType) {
            case DELETE:
                return (request, endpoint, endpointExtra) -> request.delete(String.valueOf(new Formatter().format(endpoint, endpointExtra)));
            case GET:
                return (request, endpoint, endpointExtra) -> request.get(String.valueOf(new Formatter().format(endpoint, endpointExtra)));
            case PUT:
                return (request, endpoint, endpointExtra) -> request.put(String.valueOf(new Formatter().format(endpoint, endpointExtra)));
            case POST:
                return (request, endpoint, endpointExtra) -> request.post(String.valueOf(new Formatter().format(endpoint, endpointExtra)));
            default:
                return null;
        }
    }
}
