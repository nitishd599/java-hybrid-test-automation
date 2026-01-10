package hooks;

import core.driver.DriverFactory;
import core.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        DriverFactory.initDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {
            attachScreenshot(scenario);
        }

        DriverFactory.quitDriver();
    }

    private void attachScreenshot(Scenario scenario) {
        try {
            byte[] screenshot;

            if (DriverManager.getWebDriver() != null) {
                screenshot = ((TakesScreenshot) DriverManager.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
            } else {
                screenshot = ((TakesScreenshot) DriverManager.getMobileDriver())
                        .getScreenshotAs(OutputType.BYTES);
            }

            scenario.attach(screenshot, "image/png", "Failure Screenshot");

        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
