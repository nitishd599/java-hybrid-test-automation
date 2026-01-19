package core.config;

import core.config.pojo.*;

public final class ConfigManager {

    private static ConfigManager instance;

    private final RootConfig rootConfig;
    private final EnvironmentConfig activeEnvConfig;
    private final String activeEnv;

    private ConfigManager() {
        rootConfig = YamlConfigLoader.load();
        activeEnv = resolveEnvironment();
        activeEnvConfig = getEnvironmentConfig(activeEnv);
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private String resolveEnvironment() {
        String envFromSystem = System.getProperty("env");
        return (envFromSystem != null && !envFromSystem.isEmpty())
                ? envFromSystem
                : rootConfig.getDefaultEnv();
    }

    private EnvironmentConfig getEnvironmentConfig(String env) {
        EnvironmentConfig config = rootConfig.getEnvironments().get(env);
        if (config == null) {
            throw new RuntimeException("Environment not found in YAML: " + env);
        }
        return config;
    }

    /* ---------- PUBLIC GETTERS ---------- */

    public String getActiveEnv() {
        return activeEnv;
    }

    public String getWebBaseUrl() {
        return activeEnvConfig.getWeb().getBaseUrl();
    }

    public String getBrowser() {
        return activeEnvConfig.getWeb().getBrowser();
    }

    public boolean isHeadless() {
        return activeEnvConfig.getWeb().isHeadless();
    }

    public String getApiBaseUrl() {
        return activeEnvConfig.getApi().getBaseUrl();
    }

    public String getMobilePlatform() {
        return activeEnvConfig.getMobile().getPlatform();
    }

    public String getDeviceName() {
        return activeEnvConfig.getMobile().getDeviceName();
    }

    public String getAppSource() {
        return activeEnvConfig.getMobile().getApp().getSource();
    }

    public String getLocalAppPath() {
        return activeEnvConfig.getMobile().getApp().getLocalPath();
    }

    public String getResolvedApp() {

        MobileConfig mobile = activeEnvConfig.getMobile();
        AppConfig app = mobile.getApp();

        if ("local".equalsIgnoreCase(app.getSource())) {
            return app.getLocalPath();
        }

        String cloud = mobile.getCloud();
        CloudProviderAppConfig providerConfig =
                app.getCloud().get(cloud);

        if (providerConfig == null) {
            throw new RuntimeException(
                    "No appId configured for cloud: " + cloud
            );
        }

        return providerConfig.getAppId();
    }

    public String getCloudProvider() {
        return activeEnvConfig.getMobile().getCloud();
    }

    public boolean isApiRequestLoggingEnabled() {
        return activeEnvConfig.getApi().getLogging().isRequest();
    }

    public boolean isApiResponseLoggingEnabled() {
        return activeEnvConfig.getApi().getLogging().isResponse();
    }
}
