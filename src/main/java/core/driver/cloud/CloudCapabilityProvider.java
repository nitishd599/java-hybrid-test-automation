package core.driver.cloud;

import org.openqa.selenium.remote.DesiredCapabilities;

public interface CloudCapabilityProvider {

    DesiredCapabilities getCapabilities();
    String getRemoteUrl();
}
