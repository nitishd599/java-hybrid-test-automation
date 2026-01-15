package stepdefinitions.api;

import core.api.constants.ApiConstants;
import core.api.utils.ApiUtils;
import core.context.ScenarioContext;
import assertions.core.ValidationUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ApiSteps {

    @When("I send a GET request to {string}")
    public void i_send_get_request(String endpoint) {
        ApiUtils.get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void verify_status_code(int statusCode) {
        Response response = ScenarioContext.get(ApiConstants.RESPONSE);
        ValidationUtils.verifyEquals(
                String.valueOf(response.getStatusCode()),
                String.valueOf(statusCode),
                "Status code mismatch"
        );
    }
}