package core.config.pojo;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloudAppsConfig {

    /**
     * Key = cloud provider name (browserstack, lambdatest)
     * Value = provider config
     */
    private Map<String, CloudProviderAppConfig> providers;

    public CloudProviderAppConfig get(String provider) {
        return providers.get(provider);
    }

}
