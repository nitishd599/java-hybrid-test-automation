package core.api.utils;

import core.context.ScenarioContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PlaceholderResolver {

    private static final Pattern PLACEHOLDER_PATTERN =
            Pattern.compile("\\{\\{(.*?)}}");

    private PlaceholderResolver() {}

    public static String resolve(String input) {
        if (input == null || !input.contains("{{")) {
            return input;
        }

        Matcher matcher = PLACEHOLDER_PATTERN.matcher(input);
        StringBuffer resolved = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(1);

            Object value = ScenarioContext.get(key);
            if (value == null) {
                throw new RuntimeException(
                        "No value found in ScenarioContext for placeholder: " + key
                );
            }

            matcher.appendReplacement(
                    resolved,
                    Matcher.quoteReplacement(value.toString())
            );
        }

        matcher.appendTail(resolved);
        return resolved.toString();
    }
}
