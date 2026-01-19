package core.utils;

import core.config.ConfigManager;

public final class AppResolver {

    private AppResolver() {}

    public static String resolve() {
        ConfigManager config = ConfigManager.getInstance();
        String source = config.getAppSource();

        if ("local".equalsIgnoreCase(source)) {
            return resolveLocalApp(config);
        }

        if ("browserstack".equalsIgnoreCase(source)) {
            return config.getBrowserStackAppId();
        }

        if ("lambdatest".equalsIgnoreCase(source)) {
            return config.getLambdaTestAppId();
        }

        throw new RuntimeException("Unsupported app source: " + source);
    }

    private static String resolveLocalApp(ConfigManager config) {
        String path = config.getLocalAppPath();

        if (path == null || path.isEmpty()) {
            throw new RuntimeException("Local app path is not configured");
        }

        return System.getProperty("user.dir") + path;
    }
}
