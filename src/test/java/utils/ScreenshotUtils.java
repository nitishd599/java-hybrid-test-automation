package utils;

import core.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    private static WebDriver getActiveDriver() {
        if (DriverManager.getWebDriver() != null) {
            return DriverManager.getWebDriver();
        }
        if (DriverManager.getMobileDriver() != null) {
            return DriverManager.getMobileDriver();
        }
        throw new RuntimeException("No active driver found for screenshot capture");
    }

    /** For Cucumber / Allure attachment */
    public static byte[] captureAsBytes() {
        return ((TakesScreenshot) getActiveDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    /** For Extent / Jenkins artifacts */
    public static String captureAndSave(String testName) {
        File source = ((TakesScreenshot) getActiveDriver())
                .getScreenshotAs(OutputType.FILE);

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String destinationPath = System.getProperty("user.dir")
                + "/target/screenshots/"
                + testName + "_" + timestamp + ".png";

        try {
            Files.createDirectories(new File(destinationPath).getParentFile().toPath());
            Files.copy(source.toPath(), new File(destinationPath).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }

        return destinationPath;
    }
}
