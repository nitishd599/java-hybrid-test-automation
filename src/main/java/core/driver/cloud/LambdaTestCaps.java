package core.driver.cloud;

import core.config.ConfigManager;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LambdaTestCaps implements CloudCapabilityProvider {

    @Override
    public DesiredCapabilities getCapabilities() {
        ConfigManager config = ConfigManager.getInstance();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", config.getDeviceName());
        caps.setCapability("app", config.getLambdaTestAppId());

        caps.setCapability("LT:Options", new java.util.HashMap<String, Object>() {{
            put("project", "Automation Framework");
            put("build", "Mobile Build");
            put("name", "Android Test");
        }});

        return caps;
    }

    @Override
    public String getRemoteUrl() {
        return "https://" +
                System.getenv("LT_USERNAME") + ":" +
                System.getenv("LT_ACCESS_KEY") +
                "@mobile-hub.lambdatest.com/wd/hub";
    }
}
