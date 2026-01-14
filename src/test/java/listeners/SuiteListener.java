package listeners;

import com.aventstack.extentreports.ExtentReports;
import core.logging.FrameworkLogger;
import core.reports.ExtentManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    private ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        // Initialize report once per suite
        extent = ExtentManager.getExtent();
        FrameworkLogger.info("=== Test Suite Started: " + suite.getName() + " ===");
    }

    @Override
    public void onFinish(ISuite suite) {
        // Flush report once per suite
        if (extent != null) {
            extent.flush();
        }
        FrameworkLogger.info("=== Test Suite Finished: " + suite.getName() + " ===");
    }
}