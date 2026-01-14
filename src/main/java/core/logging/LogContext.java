package core.logging;

import org.apache.logging.log4j.ThreadContext;

public final class LogContext {

    private LogContext() {}

    public static void setScenarioName(String scenarioName) {
        ThreadContext.put("scenario", scenarioName);
    }

    public static void clear() {
        ThreadContext.clearAll();
    }
}
