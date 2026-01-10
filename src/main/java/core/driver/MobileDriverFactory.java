package core.driver;

import core.config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public final class MobileDriverFactory {

    private MobileDriverFactory() {}

    public static void createAndSetDriver() {
        try {
            ConfigManager config = ConfigManager.getInstance();

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", config.getDeviceName());
            caps.setCapability("app", config.getAppPath());
            caps.setCapability("automationName", "UiAutomator2");

            AppiumDriver driver = new AndroidDriver(
                    new URL("http://localhost:4723/wd/hub"),
                    caps
            );

            DriverManager.setMobileDriver(driver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Android driver", e);
        }
    }
}
