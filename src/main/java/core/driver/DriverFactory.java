package core.driver;

import core.config.ConfigManager;

public final class DriverFactory {

    private DriverFactory() {}

    public static void initDriver() {
        String platform = ConfigManager.getInstance().getMobilePlatform();

        if (platform == null || platform.equalsIgnoreCase("web")) {
            WebDriverFactory.createAndSetDriver();
        } else if (platform.equalsIgnoreCase("android")) {
            MobileDriverFactory.createAndSetDriver();
        } else {
            throw new RuntimeException("Unsupported platform: " + platform);
        }
    }

    public static void quitDriver() {
        if (DriverManager.getWebDriver() != null) {
            DriverManager.getWebDriver().quit();
            DriverManager.unloadWebDriver();
        }

        if (DriverManager.getMobileDriver() != null) {
            DriverManager.getMobileDriver().quit();
            DriverManager.unloadMobileDriver();
        }
    }
}
