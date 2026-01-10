package base;

import core.driver.DriverManager;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver driver() {
        return DriverManager.getWebDriver();
    }
}
