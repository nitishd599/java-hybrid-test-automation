package core.api.payload;

import core.utils.JsonUtils;

import java.util.Map;

public final class PayloadBuilder {

    private PayloadBuilder() {}

    /**
     * Convert POJO → JSON String
     */
    public static String fromPojo(Object pojo) {
        return JsonUtils.toJson(pojo);
    }

    /**
     * Convert Map → JSON String
     */
    public static String fromMap(Map<String, Object> payload) {
        return JsonUtils.toJson(payload);
    }
}
