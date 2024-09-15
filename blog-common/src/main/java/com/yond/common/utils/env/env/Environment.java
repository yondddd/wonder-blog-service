package com.yond.common.utils.env.env;


import java.util.function.Function;

public class Environment {

    private static final EnvCache envCache = EnvCache.SINGLETON;

    public static <T> T getValue(final String key, final Function<String, T> function) {
        return envCache.getValue(key, function);
    }

    public static String getProperty(final String key) {
        return envCache.getProperty(key);
    }

    public static void setProperty(final String key, final String value) {
        envCache.setProperty(key, value);
    }

    public static String getEnv() {
        return envCache.getEnv();
    }


}