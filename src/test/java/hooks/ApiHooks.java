package hooks;

import core.api.builder.RequestBuilder;
import core.api.constants.ApiConstants;
import core.context.ScenarioContext;
import core.logging.FrameworkLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApiHooks {

    @Before("@api")
    public void beforeApiScenario(Scenario scenario) {
        FrameworkLogger.info("Starting API Scenario: " + scenario.getName());
        ScenarioContext.set(
                ApiConstants.REQUEST_SPEC,
                RequestBuilder.baseRequest()
        );
    }

    @After("@api")
    public void afterApiScenario(Scenario scenario) {
        FrameworkLogger.info("Finished API Scenario: " + scenario.getName());
        ScenarioContext.clear();
    }
}
