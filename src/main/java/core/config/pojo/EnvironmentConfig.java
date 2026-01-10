package core.config.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvironmentConfig {
    private WebConfig web;
    private MobileConfig mobile;
    private ApiConfig api;
}
