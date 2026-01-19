package core.config.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiLoggingConfig {
    private boolean request;
    private boolean response;
}
