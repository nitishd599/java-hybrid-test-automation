package assertions.core;

public final class ValidationUtils {

    private ValidationUtils() {}

    public static void verifyEquals(
            String actual,
            String expected,
            String message
    ) {
        AssertionManager.get()
                .assertEquals(actual, expected, message);
    }

    public static void verifyTrue(
            boolean condition,
            String message
    ) {
        AssertionManager.get()
                .assertTrue(condition, message);
    }

    public static void verifyFalse(
            boolean condition,
            String message
    ) {
        AssertionManager.get()
                .assertFalse(condition, message);
    }
}
