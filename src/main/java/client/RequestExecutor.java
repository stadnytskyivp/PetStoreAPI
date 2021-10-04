package client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

interface RequestExecutor {
    Response executeRequest(RequestSpecification reqSpec, String endpoint, String endpointExtra);
}
