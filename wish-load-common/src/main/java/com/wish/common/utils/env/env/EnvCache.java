package com.wish.common.utils.env.env;


import com.wish.common.utils.env.enums.EnvEnum;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Properties;
import java.util.function.Function;

/**
 * 服务运行时的环境处理类cache
 **/
final class EnvCache {

    /**
     * 环境配制文件
     */
    private static final String EVN_CONFIG = "/data/env/app.env";
    /**
     * 应用配制文件
     */
    private static final String APP_EVN_CONFIG = "META-INF/app.properties";
    /**
     * 应用配制文件(spring boot [application.properties]
     */
    private static final String APPLICATION_EVN_CONFIG = "application.properties";
    /**
     * 默认的环境变量
     */
    private static final String DEFAULT_DEPLOY_ENV = "dev";
    /**
     * 发布的环境变量
     */
    private static final String KEY_DEPLOY_ENV = "deployenv";
    /**
     * appkey变量名
     */
    private static final String APPKEY = "appkey";
    /**
     * 阿里云机器
     */
    private static final String MACHINE_ID_NAME = "HOSTNAME";
    /**
     * 环境变量值
     */
    private final Properties defaultProperty = new Properties();
    /**
     * APP 名称
     */
    private String appkey;
    /**
     * 项目步属环境
     */
    private String deployEnv;
    /**
     * 项目步属环境
     */
    private EnvEnum envEnum;
    /**
     * 机器ID
     */
    private int machineId = 0;

    private String gatewayPath;

    static EnvCache SINGLETON = new EnvCache();

    private EnvCache() {
        this.init();
    }

    private void init() {

        this.loadAppEnv();

        //get appkey
        this.appkey = this.defaultProperty.getProperty(APPKEY);

        this.appkey = StringUtils.trimToNull(appkey);

        //get deploy env
        this.deployEnv = this.defaultProperty.getProperty(KEY_DEPLOY_ENV);

        //get env enum
        this.envEnum = EnvEnum.getEnvEnum(deployEnv);

        String machineName = getProperty("machine_id", "0");
        if (StringUtils.isNotBlank(machineName)) {
            this.machineId = NumberUtils.toInt(machineName, 0);
        } else {
            machineName = getProperty(MACHINE_ID_NAME, "-0");
            if (StringUtils.isNotBlank(machineName)) {
                int index = machineName.lastIndexOf("-");
                if (index > 0) {
                    machineName = machineName.substring(index + 1);
                    this.machineId = NumberUtils.toInt(machineName, 0);
                }
            }
        }

        System.out.printf("Env-Config[loaded appenv: env [%s], appkey [%s]]%n", envEnum.getName(), appkey);
    }

    <T> T getValue(final String key, final Function<String, T> function) {
        return getValue(key, function, null);
    }

    <T> T getValue(final String key, Function<String, T> function, T defaultValue) {
        final String value = this.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }

        try {
            return function.apply(value);
        } catch (Throwable ex) {
            return defaultValue;
        }
    }

    String getProperty(final String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        String value = defaultProperty.getProperty(key);
        if (null == value) {
            value = System.getProperty(key);
            if (value == null) {
                value = System.getenv(key);
            }
        }

        return value;
    }

    void setProperty(final String key, final String value) {
        this.defaultProperty.setProperty(key, value);
    }

    String getProperty(final String key, final String defaultValue) {
        final String value = this.getProperty(key);
        return value == null ? defaultValue : value;
    }

    void remove(String key) {
        defaultProperty.remove(key);
    }

    Properties getProperties() {
        return defaultProperty;
    }

    String getAppKey() {
        return appkey;
    }

    void setAppKey(String appKey) {
        appkey = appKey;
    }

    String getEnv() {
        return deployEnv;
    }

    public EnvEnum getEnvEnum() {
        return envEnum;
    }

    boolean contains(String key) {
        return defaultProperty.containsKey(key);
    }

    private void loadAppEnv() {
        //从本地项目中文件中加载
        Properties props = loadApplicationConfig();
        if (MapUtils.isNotEmpty(props)) {
            defaultProperty.putAll(props);
        }

        //从本机中加载
        props = loadConfigFromMachine();

        if (null != props) {
            defaultProperty.putAll(props);
        }

        setPropertyValue(APPKEY);

        setPropertyValue(KEY_DEPLOY_ENV);

        props = getDefaultAppEnv();

        if (MapUtils.isNotEmpty(props)) {
            defaultProperty.putAll(props);
        }
    }

    private void setPropertyValue(String key) {
        //从vm中加载
        String value = System.getProperty(key);
        if (StringUtils.isNotBlank(value)) {
            defaultProperty.put(key, value);
        }

        // 从env中加载
        value = System.getenv(key);
        if (StringUtils.isNotBlank(value)) {
            defaultProperty.put(key, value);
        }
    }

    private Properties loadConfigFromMachine() {
        String loadFile = null;
        Properties props;
        try {
            loadFile = EVN_CONFIG;
            // load from /data/env/appenv
            props = PropertiesUtil.loadFromFileSystem(loadFile);
            if (props == null) {
                props = PropertiesUtil.loadFromClassPath(loadFile);
            }
        } catch (Throwable e) {
            props = null;
            System.err.println("failed to load data from " + loadFile);
        }
        return props;
    }

    private Properties loadApplicationConfig() {
        String loadFile = null;
        Properties props = null;

        try {
            // load from META-INF/app.properties
            loadFile = APP_EVN_CONFIG;
            props = PropertiesUtil.loadFromClassPath(loadFile);
        } catch (Throwable e) {
            System.err.println("failed to load data from " + loadFile);
        }

        if (null != props) {
            props.remove(KEY_DEPLOY_ENV);
        }

        if (null == props) {
            props = new Properties();
        }

        // load from application.properties
        loadFile = APPLICATION_EVN_CONFIG;
        final Properties springBootProps = getProperties(loadFile);
        if (null != springBootProps && springBootProps.size() > 0) {
            springBootProps.remove(KEY_DEPLOY_ENV);
            props.putAll(springBootProps);
        }

        return props;
    }

    private Properties getProperties(final String app) {
        Properties properties = null;
        try {
            // load from application.properties
            properties = PropertiesUtil.loadFromClassPath(app);
        } catch (Throwable e) {
            System.err.println("failed to load data from " + app);
        }
        return properties;
    }


    private Properties getDefaultAppEnv() {
        final Properties props = new Properties();
        //设置发布环境
        if (!defaultProperty.containsKey(KEY_DEPLOY_ENV)) {
            props.put(KEY_DEPLOY_ENV, DEFAULT_DEPLOY_ENV);
        }
        return props;
    }

    public int getMachineId() {
        return machineId;
    }

    public String getGatewayPath() {
        return gatewayPath;
    }

}