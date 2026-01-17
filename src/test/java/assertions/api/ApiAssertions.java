package assertions.api;

import assertions.core.AssertionManager;
import core.api.constants.ApiConstants;
import core.context.ScenarioContext;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public final class ApiAssertions {

    private ApiAssertions() {}

    private static Response response() {
        return ScenarioContext.get(ApiConstants.RESPONSE);
    }

    public static void statusCodeIs(int expected) {
        AssertionManager.get()
                .assertEquals(
                        response().getStatusCode(),
                        expected,
                        "Status code mismatch"
                );
    }

    public static void bodyFieldEquals(String jsonPath, Object expected) {
        Object actual = response().jsonPath().get(jsonPath);

        AssertionManager.get()
                .assertEquals(
                        actual,
                        expected,
                        "Mismatch for jsonPath: " + jsonPath
                );
    }

    public static void bodyFieldNotNull(String jsonPath) {
        Object value = response().jsonPath().get(jsonPath);

        AssertionManager.get()
                .assertNotNull(
                        value,
                        "Expected non-null value for: " + jsonPath
                );
    }

    public static void responseTimeLessThan(long millis) {
        AssertionManager.get()
                .assertTrue(
                        response().getTime() < millis,
                        "Response time exceeded: " + millis + " ms"
                );
    }

    public static void verifySchema(String schemaFileName) {
        Response response = response();
        response.then().assertThat()
                .body(matchesJsonSchema(
                        new File("src/test/resources/schemas/" + schemaFileName)
                ));
    }
}
