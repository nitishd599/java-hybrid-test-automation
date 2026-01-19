package core.api.utils;

import core.api.constants.ApiConstants;
import core.config.ConfigManager;
import core.context.ScenarioContext;
import core.logging.FrameworkLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class ApiUtils {

    private ApiUtils() {}

    public static Response get(String endpoint) {
        String resolvedEndpoint = PlaceholderResolver.resolve(endpoint);

        FrameworkLogger.info("GET " + resolvedEndpoint);

        Response response = getRequest()
                .get(resolvedEndpoint);

        storeResponse(response);
        return response;
    }

    public static Response post(String endpoint, Object body) {
        String resolvedEndpoint = PlaceholderResolver.resolve(endpoint);

        FrameworkLogger.info("POST " + resolvedEndpoint);

        Response response = getRequest()
                .body(body)
                .post(resolvedEndpoint);

        storeResponse(response);
        return response;
    }

    public static Response put(String endpoint, Object body) {
        String resolvedEndpoint = PlaceholderResolver.resolve(endpoint);

        FrameworkLogger.info("PUT " + resolvedEndpoint);

        Response response = getRequest()
                .body(body)
                .put(resolvedEndpoint);

        storeResponse(response);
        return response;
    }

    private static RequestSpecification getRequest() {
        return ScenarioContext.get(ApiConstants.REQUEST_SPEC);
    }

    private static void storeResponse(Response response) {
        if (ConfigManager.getInstance().isApiResponseLoggingEnabled()) {
            response.then().log().all();
        }
        ScenarioContext.set(ApiConstants.RESPONSE, response);
    }
}
