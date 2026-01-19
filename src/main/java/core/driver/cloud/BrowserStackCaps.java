package core.driver.cloud;

import core.config.ConfigManager;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackCaps implements CloudCapabilityProvider {

    @Override
    public DesiredCapabilities getCapabilities() {
        ConfigManager config = ConfigManager.getInstance();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", config.getDeviceName());
        caps.setCapability("app", config.getBrowserStackAppId());

        caps.setCapability("bstack:options", new java.util.HashMap<String, Object>() {{
            put("projectName", "Automation Framework");
            put("buildName", "Mobile Tests");
            put("sessionName", "Android Test");
        }});

        return caps;
    }

    @Override
    public String getRemoteUrl() {
        return "https://" +
                System.getenv("BROWSERSTACK_USERNAME") + ":" +
                System.getenv("BROWSERSTACK_ACCESS_KEY") +
                "@hub-cloud.browserstack.com/wd/hub";
    }
}
