package core.api.builder;

import core.api.auth.AuthManager;
import core.config.ConfigManager;
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

        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getInstance().getApiBaseUrl())
                .setRelaxedHTTPSValidation()
                .addHeader("Content-Type", "application/json")
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification authRequest() {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseRequest())
                .addHeader("Authorization", "Bearer " + AuthManager.getToken())
                .build();
    }

    /** Enrich request with headers/body */
    public static RequestSpecification with(
            RequestSpecification base,
            Map<String, String> headers,
            Object body
    ) {
        if (headers != null && !headers.isEmpty()) {
            FrameworkLogger.debug("Adding headers: " + headers);
            base.headers(headers);
        }

        if (body != null) {
            FrameworkLogger.debug("Adding request body");
            base.body(body);
        }

        return base;
    }
}
