package core.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FrameworkLogger {

    private FrameworkLogger() {}

    private static Logger getLogger() {
        Class<?> callerClass = StackWalker
                .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass();

        return LogManager.getLogger(callerClass);
    }

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void error(String message) {
        getLogger().error(message);
    }

    public static void debug(String message) {
        getLogger().debug(message);
    }

    public static void warn(String message) {
        getLogger().warn(message);
    }
}