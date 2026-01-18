package core.actions;

import core.driver.DriverManager;
import core.wait.MobileWaits;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class MobileActions {

    private final AppiumDriver driver;

    public MobileActions() {
        this.driver = DriverManager.getMobileDriver();
    }

    /* ---------------- BASIC ACTIONS ---------------- */

    public void type(By locator, String text) {
        WebElement element = MobileWaits.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void tap(By locator) {
        WebElement element = MobileWaits.waitForClickable(locator);
        element.click();
    }

    /* ---------------- GESTURE ACTIONS ---------------- */

    public void tapByCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                x, y
        ));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    public void tapCenter(By locator) {
        WebElement element = MobileWaits.waitForVisible(locator);
        Point p = element.getLocation();
        Dimension d = element.getSize();

        tapByCoordinates(
                p.getX() + d.getWidth() / 2,
                p.getY() + d.getHeight() / 2
        );
    }

    public void longPress(int x, int y, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        longPress.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                x, y
        ));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger, duration));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(longPress));
    }

    public void swipe(int startX, int startY, int endX, int endY, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                startX, startY
        ));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(
                duration,
                PointerInput.Origin.viewport(),
                endX, endY
        ));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();

        swipe(
                size.width / 2,
                (int) (size.height * 0.8),
                size.width / 2,
                (int) (size.height * 0.2),
                Duration.ofMillis(600)
        );
    }

    public void scrollUp() {
        Dimension size = driver.manage().window().getSize();

        swipe(
                size.width / 2,
                (int) (size.height * 0.2),
                size.width / 2,
                (int) (size.height * 0.8),
                Duration.ofMillis(600)
        );
    }

    public void dragAndDrop(By source, By target) {
        WebElement src = MobileWaits.waitForVisible(source);
        WebElement tgt = MobileWaits.waitForVisible(target);

        Point srcPoint = src.getLocation();
        Point tgtPoint = tgt.getLocation();

        swipe(
                srcPoint.getX(),
                srcPoint.getY(),
                tgtPoint.getX(),
                tgtPoint.getY(),
                Duration.ofMillis(800)
        );
    }

    public void scrollUntilVisible(By locator, int maxAttempts) {
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                if (driver.findElement(locator).isDisplayed()) {
                    return;
                }
            } catch (Exception ignored) {}

            scrollDown();
            attempts++;
        }

        throw new RuntimeException("Element not found after scrolling: " + locator);
    }
}
