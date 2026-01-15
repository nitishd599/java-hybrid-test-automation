package assertions.core;

import org.testng.asserts.SoftAssert;

public final class AssertionManager {

    private static final ThreadLocal<SoftAssert> softAssert =
            ThreadLocal.withInitial(SoftAssert::new);

    private AssertionManager() {}

    public static SoftAssert get() {
        return softAssert.get();
    }

    public static void assertAll() {
        get().assertAll();
        softAssert.remove();
    }
}
