package core.api.auth;

import core.context.ScenarioContext;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public final class AuthManager {

    private static String token;

    private AuthManager() {}

    public static String getToken() {
        if (token == null) {
            token = generateToken();
        }
        return token;
    }

    private static String generateToken() {
        Response response =
                RestAssured
                        .given()
                        .contentType("application/json")
                        .body("{\"username\":\"admin\",\"password\":\"admin\"}")
                        .post("/auth/login");

        return response.jsonPath().getString("token");
    }

    public static void reset() {
        token = null;
    }
}
