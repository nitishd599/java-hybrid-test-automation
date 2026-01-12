package hooks;

import context.ScenarioContext;
import utils.ScreenshotUtils;
import core.driver.DriverFactory;
import assertions.AssertionManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        DriverFactory.initDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtils.captureAsBytes();
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }

        ScenarioContext.clear();
        AssertionManager.assertAll();
        DriverFactory.quitDriver();
    }
}