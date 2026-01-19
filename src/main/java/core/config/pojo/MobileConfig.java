package core.config.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobileConfig {
    private String platform;
    private String deviceName;
    private AppConfig app;
    private String cloud;
}
