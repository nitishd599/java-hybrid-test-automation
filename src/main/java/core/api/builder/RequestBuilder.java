package core.api.builder;

import core.api.auth.AuthManager;
import core.api.constants.ApiConstants;
import core.config.ConfigManager;
import core.context.ScenarioContext;
import core.logging.FrameworkLogger;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public final class RequestBuilder {

    private RequestBuilder() {}

    /** Base request — used in Hooks */
    public static RequestSpecification baseRequest() {

        FrameworkLogger.info("Creating base API request");

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getInstance().getApiBaseUrl())
                .setRelaxedHTTPSValidation()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json");

        if (ConfigManager.getInstance().isApiRequestLoggingEnabled()) {
            builder.log(LogDetail.ALL);
        }

        return builder.build();
    }

    public static RequestSpecification authRequest() {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseRequest())
                .addHeader("Authorization", "Bearer " + AuthManager.getToken())
                .build();
    }

    /** Always return a NEW spec (no mutation) */
    public static RequestSpecification with(
            RequestSpecification base,
            Map<String, String> headers,
            Object body
    ) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .addRequestSpecification(base);

        if (headers != null && !headers.isEmpty()) {
            FrameworkLogger.debug("Adding headers: " + headers);
            builder.addHeaders(headers);
        }

        if (body != null) {
            FrameworkLogger.debug("Adding request body");
            builder.setBody(body);
        }

        return builder.build();
    }

    private static void store(RequestSpecification request) {
        ScenarioContext.set(ApiConstants.REQUEST_SPEC, request);
    }

    public static void baseRequestAndStore() {
        store(baseRequest());
    }

    public static void authRequestAndStore() {
        store(authRequest());
    }

    public static void withAndStore(Map<String, String> headers, Object body) {
        RequestSpecification base =
                ScenarioContext.get(ApiConstants.REQUEST_SPEC);
        store(with(base, headers, body));
    }
}
