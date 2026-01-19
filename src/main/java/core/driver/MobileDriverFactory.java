package core.driver;

import core.config.ConfigManager;
import core.driver.cloud.BrowserStackCaps;
import core.driver.cloud.CloudCapabilityProvider;
import core.driver.cloud.LambdaTestCaps;
import core.utils.AppResolver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public final class MobileDriverFactory {

    private MobileDriverFactory() {}

    public static void createAndSetDriver() {
        try {
            ConfigManager config = ConfigManager.getInstance();
            String cloud = config.getCloudProvider();

            if (cloud == null || cloud.equalsIgnoreCase("local")) {
                createLocalDriver();
                return;
            }

            CloudCapabilityProvider provider = getProvider(cloud);

            AppiumDriver driver = new AppiumDriver(
                    new URL(provider.getRemoteUrl()),
                    provider.getCapabilities()
            );

            DriverManager.setMobileDriver(driver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize mobile driver", e);
        }
    }

    private static CloudCapabilityProvider getProvider(String cloud) {
        switch (cloud.toLowerCase()) {
            case "browserstack":
                return new BrowserStackCaps();
            case "lambdatest":
                return new LambdaTestCaps();
            default:
                throw new RuntimeException("Unsupported cloud provider: " + cloud);
        }
    }

    private static void createLocalDriver() {
        try {
            ConfigManager config = ConfigManager.getInstance();

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", config.getMobilePlatform());
            caps.setCapability("deviceName", config.getDeviceName());
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("app", AppResolver.resolve());

            AppiumDriver driver = new AndroidDriver(
                    new URL("http://localhost:4723/wd/hub"),
                    caps
            );

            DriverManager.setMobileDriver(driver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize local Appium driver", e);
        }
    }
}
