package core.config.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RootConfig {
    private String defaultEnv;
    private Map<String, EnvironmentConfig> environments;
}
