package core.context;

import core.api.builder.RequestBuilder;
import core.api.constants.ApiConstants;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public final class ApiTestContext {

    private ApiTestContext() {}

    public static void createRequest(
            Map<String, String> headers,
            Object body
    ) {
        RequestSpecification request =
                RequestBuilder.buildRequest(headers, body);

        ScenarioContext.set(ApiConstants.REQUEST_SPEC, request);
    }
}
