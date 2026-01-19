package core.config.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppConfig {
    private String source;
    private String localPath;
    private CloudAppsConfig cloud;
}
