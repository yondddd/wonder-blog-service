package com.yond.env.env;


import com.yond.env.enums.EnvEnum;

import java.util.List;
import java.util.Properties;
import java.util.function.Function;

/**
 * 服务运行时的环境处理类
 *
 * @version 1.0
 * @created 2018/08/23 13:51
 **/
public class Environment {

    private static final EnvCache envCache = EnvCache.SINGLETON;

    public static final int getInt(final String key, int defaultValue) {
        return envCache.getInt(key, defaultValue);
    }

    public static final boolean getBoolean(final String key) {
        return envCache.getBoolean(key);
    }

    public static final boolean getBoolean(final String key, boolean defaultValue) {
        return envCache.getBoolean(key, defaultValue);
    }

    public static final long getLong(final String key, long defaultValue) {
        return envCache.getLong(key, defaultValue);
    }

    public static final double getDouble(final String key, double defaultValue) {
        return envCache.getDouble(key, defaultValue);
    }

    public static final <T> T getValue(final String key, final Function<String, T> function) {
        return envCache.getValue(key, function);
    }

    public static final <T> T getValue(final String key, Function<String, T> function, T defaultValue) {
        return envCache.getValue(key, function, defaultValue);
    }

    public static final String getProperty(final String key) {
        return envCache.getProperty(key);
    }

    public static final void setProperty(final String key, final String value) {
        envCache.setProperty(key, value);
    }

    public static final String getProperty(final String key, final String defaultValue) {
        return envCache.getProperty(key, defaultValue);
    }

    public static final void remove(String key) {
        envCache.remove(key);
    }

    public static final Properties getProperties() {
        return envCache.getProperties();
    }

    public static final boolean isRpcGrayPublish() {
        return false;
    }

    public static final String getAppKey() {
        return envCache.getAppKey();
    }

    public static final void setAppKey(String appKey) {
        envCache.setAppKey(appKey);
    }


    public static final EnvEnum getEnvEnum() {
        return envCache.getEnvEnum();
    }

    public static final String getEnv() {
        return envCache.getEnv();
    }

    public static final String getenv( String name) {
        return System.getenv(name);
    }

    public static final String getVm( String name) {
        return System.getProperty(name);
    }

    public static final String getVm( String name, String defaultValue) {
        return System.getProperty(name, defaultValue);
    }

    public static final String getConfigCenter() {
        return envCache.getConfigCenter();
    }



    public static final boolean isDevEnv() {
        return envCache.isDevEnv();
    }


    public static final boolean isDevEnv(final String env) {
        return envCache.isDevEnv(env);
    }

    public static final boolean isTestEnv(final String env) {
        return envCache.isTestEnv(env);
    }


    public static final boolean isProdEnv() {
        return envCache.isProdEnv();
    }

    public static final String getProdEnv() {
        return envCache.getProdEnv();
    }

    public static final String getTestEnv() {
        return envCache.getTestEnv();
    }

    public static final String getDevEnv() {
        return envCache.getDevEnv();
    }


    public static final List<String> getPhones() {
        return envCache.getPhones();
    }

    public static final int getMachineId() {
        return envCache.getMachineId();
    }

    public static final String getGatewayPath() {
        return envCache.getGatewayPath();
    }
}