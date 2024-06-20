package com.yond.env.env;

import java.util.HashMap;
import java.util.Map;

/**
 * ProgramArgumentsEnvironment
 *
 * @version 1.0
 * @created 2019/12/12 17:03
 * @since 1.3.5
 **/
public class ProgramArgumentsEnvironment {

    private static final Map<String, String> cache = new HashMap<>();

    public static void putValue(final String key, final String value) {
        if (null == value) {
            cache.remove(key);
        } else {
            cache.put(key, value);
        }
    }

    public static String getOrDefault(String key, String defaultValue) {
        return cache.getOrDefault(key, defaultValue);
    }

    public static String get(String key) {
        return cache.get(key);
    }

    public static boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    public static Map<String, String> getCache() {
        return cache;
    }
}
