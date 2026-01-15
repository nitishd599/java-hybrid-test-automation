package core.api.client;

import core.config.ConfigManager;
import core.logging.FrameworkLogger;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public final class ApiClient {

    private ApiClient() {}

    public static RequestSpecification getBaseRequest() {

        FrameworkLogger.info("Initializing API base request");

        return RestAssured.given()
                .baseUri(ConfigManager.getInstance().getApiBaseUrl())
                .relaxedHTTPSValidation()
                .contentType("application/json");
    }
}
