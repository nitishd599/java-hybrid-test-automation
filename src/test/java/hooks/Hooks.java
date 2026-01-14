package hooks;

import context.ScenarioContext;
import core.driver.DriverFactory;
import core.logging.FrameworkLogger;
import core.logging.LogContext;
import core.reports.ExtentTestManager;
import assertions.AssertionManager;
import utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        LogContext.setScenarioName(scenario.getName());
        FrameworkLogger.info("Starting Scenario: " + scenario.getName());
        DriverFactory.initDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            // Attach to Cucumber report
            byte[] screenshot = ScreenshotUtils.captureAsBytes();
            scenario.attach(screenshot, "image/png", "Failure Screenshot");

            // Attach to Extent report
            String path = ScreenshotUtils.captureAndSave(scenario.getName());
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
        }

        FrameworkLogger.info("Finished Scenario: " + scenario.getName());

        AssertionManager.assertAll();
        ScenarioContext.clear();
        DriverFactory.quitDriver();
        LogContext.clear();
        ExtentTestManager.unload();
    }
}
