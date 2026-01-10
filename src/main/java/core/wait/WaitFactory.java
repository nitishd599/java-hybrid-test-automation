package core.wait;

import core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitFactory {

    private static final int DEFAULT_TIMEOUT = 15;

    private WaitFactory() {}

    public static WebElement waitForElement(By locator, WaitType waitType) {

        WebDriver driver = DriverManager.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));

        switch (waitType) {
            case CLICKABLE:
                return wait.until(ExpectedConditions.elementToBeClickable(locator));

            case VISIBLE:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            case PRESENCE:
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            case NONE:
                return driver.findElement(locator);

            default:
                throw new IllegalArgumentException("Invalid wait type: " + waitType);
        }
    }
}
