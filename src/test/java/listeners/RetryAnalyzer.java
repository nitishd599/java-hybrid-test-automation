package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import core.logging.FrameworkLogger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // You can increase if needed

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            FrameworkLogger.info("Retrying test " + result.getMethod().getMethodName()
                    + " - Attempt " + retryCount);
            return true; // Retry this test
        }
        return false; // Give up retrying
    }
}
