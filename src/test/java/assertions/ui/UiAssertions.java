package assertions.ui;

import assertions.core.ValidationUtils;
import org.openqa.selenium.WebElement;

public final class UiAssertions {

    private UiAssertions() {}

    public static void verifyDisplayed(WebElement element, String message) {
        ValidationUtils.verifyTrue(
                element.isDisplayed(),
                message
        );
    }

    public static void verifyTextEquals(
            WebElement element,
            String expected,
            String message
    ) {
        ValidationUtils.verifyEquals(
                element.getText(),
                expected,
                message
        );
    }

    public static void verifyEnabled(WebElement element, String message) {
        ValidationUtils.verifyTrue(
                element.isEnabled(),
                message
        );
    }
}
