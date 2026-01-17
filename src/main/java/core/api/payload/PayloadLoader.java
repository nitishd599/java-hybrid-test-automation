package core.api.payload;

import java.nio.file.Files;
import java.nio.file.Path;

public final class PayloadLoader {

    private PayloadLoader() {}

    public static String fromJsonFile(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load payload file: " + filePath, e);
        }
    }
}
