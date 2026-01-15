package core.api.utils;

import core.api.constants.ApiConstants;
import core.context.ScenarioContext;
import core.logging.FrameworkLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class ApiUtils {

    private ApiUtils() {}

    public static Response get(String endpoint) {
        FrameworkLogger.info("GET " + endpoint);
        Response response = getRequest().get(endpoint);
        storeResponse(response);
        return response;
    }

    public static Response post(String endpoint, Object body) {
        FrameworkLogger.info("POST " + endpoint);
        Response response = getRequest()
                .body(body)
                .post(endpoint);
        storeResponse(response);
        return response;
    }

    public static Response put(String endpoint, Object body) {
        FrameworkLogger.info("PUT " + endpoint);
        Response response = getRequest()
                .body(body)
                .put(endpoint);
        storeResponse(response);
        return response;
    }

    private static RequestSpecification getRequest() {
        return ScenarioContext.get(ApiConstants.REQUEST_SPEC);
    }

    private static void storeResponse(Response response) {
        ScenarioContext.set(ApiConstants.RESPONSE, response);
    }
}
