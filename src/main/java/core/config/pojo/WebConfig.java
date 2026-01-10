package core.config.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebConfig {
    private String baseUrl;
    private String browser;
    private boolean headless;
}
