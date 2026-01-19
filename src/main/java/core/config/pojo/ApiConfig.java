package core.config.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiConfig {
    private String baseUrl;
    private ApiLoggingConfig logging;
}
