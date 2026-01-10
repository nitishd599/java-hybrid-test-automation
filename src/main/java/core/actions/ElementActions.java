package core.actions;

import core.wait.WaitFactory;
import core.wait.WaitType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElementActions {

    public void click(By locator) {
        WebElement element =
                WaitFactory.waitForElement(locator, WaitType.CLICKABLE);
        element.click();
    }

    public void type(By locator, String value) {
        WebElement element =
                WaitFactory.waitForElement(locator, WaitType.VISIBLE);
        element.clear();
        element.sendKeys(value);
    }

    public String getText(By locator) {
        return WaitFactory
                .waitForElement(locator, WaitType.VISIBLE)
                .getText();
    }

    public boolean isDisplayed(By locator) {
        return WaitFactory
                .waitForElement(locator, WaitType.VISIBLE)
                .isDisplayed();
    }
}
