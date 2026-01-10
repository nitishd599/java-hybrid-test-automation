package core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import core.config.pojo.RootConfig;

import java.io.InputStream;

public final class YamlConfigLoader {

    private static final String YAML_FILE = "env.yaml";

    private YamlConfigLoader() {}

    public static RootConfig load() {
        try (InputStream is = YamlConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(YAML_FILE)) {

            if (is == null) {
                throw new RuntimeException("env.yaml not found in resources");
            }

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(is, RootConfig.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load env.yaml", e);
        }
    }
}
