package com.yond.env.env;


import com.yond.env.enums.EnvEnum;
import com.yond.env.util.PropertiesUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;
import java.util.function.Function;

/**
 * 服务运行时的环境处理类cache
 **/
final class EnvCache {

    /**
     * 开发自定义环境配置
     */
    private static final String ENV_CONFIG = "msharp.commons.env.config";

    /**
     * 本地环境文件配置
     */
    private static final String LOCAL_ENV_FILE_CONFIG = "msharp.commons.local.env.file.config";

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
     * 应用配制文件 [application.yml]
     */
    private static final String APP_EVN_CONFIG_YML = "application.yml";

    /**
     * springboot启动文件 [boot.properties]
     */
    private static final String SPRING_BOOT_CONFIG_YML = "boot.properties";

    /**
     * 默认的环境变量
     */
    private static final String DEFAULT_DEPLOY_ENV = "dev";

    /**
     * 测试环境配制中心【基于http协议】
     */
    private static final String DEFAULT_TEST_CONFIG_CENTER_URL = "http://x.com/pearl/";

    /**
     * 线上环境配制中心【基于http协议】
     */
    private static final String DEFAULT_ON_LINE_CONFIG_CENTER_URL = "http://x.cn/pearl/";


    private static final String USER_PHONE_LIST_ = "msharp.alarm.user.phone.list";

    /**
     * 发布的环境变量
     */
    private static final String KEY_DEPLOY_ENV = "deployenv";

    /**
     * appkey变量名
     */
    private static final String APPKEY = "appkey";
    /**
     * 配制中心(基于http)
     */
    private static final String CONFIG_CENTER = "configcenter";

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
     * 配制中心url(基于http)
     */
    private String configCenterUrl;
    /**
     * 钉钉报警手机号
     */
    private List<String> phones;
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

        this.loadLocalEnvConfig();

        this.loadAppEnv();

        this.loadEnvConfig();

        //get appkey
        this.appkey = this.defaultProperty.getProperty(APPKEY);

        this.appkey = StringUtils.trimToNull(appkey);

        //get deploy env
        this.deployEnv = this.defaultProperty.getProperty(KEY_DEPLOY_ENV);

        //get env enum
        this.envEnum = EnvEnum.getEnvEnum(deployEnv);

        this.configCenterUrl = this.defaultProperty.getProperty(CONFIG_CENTER);

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

        System.out.printf("MSharp-Env-Config[loaded appenv: env [%s], appkey [%s]]%n", envEnum.getName(), appkey);
    }

    int getInt(final String key, int defaultValue) {
        return NumberUtils.toInt(this.getProperty(key), defaultValue);
    }

    boolean getBoolean(final String key) {
        return BooleanUtils.toBoolean(this.getProperty(key));
    }

    boolean getBoolean(final String key, boolean defaultValue) {
        String val = this.getProperty(key);
        if (StringUtils.isBlank(val)) {
            return defaultValue;
        }

        return BooleanUtils.toBoolean(val);
    }

    long getLong(final String key, long defaultValue) {
        return NumberUtils.toLong(this.getProperty(key), defaultValue);
    }

    double getDouble(final String key, double defaultValue) {
        return NumberUtils.toDouble(this.getProperty(key), defaultValue);
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
                if (value == null) {
                    value = ProgramArgumentsEnvironment.get(key);
                }
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

    String getConfigCenter() {
        return configCenterUrl;
    }

    boolean isDevEnv() {
        return envEnum.isDevEnv();
    }


    boolean isDevEnv(final String env) {
        return EnvEnum.DEV.getName().equalsIgnoreCase(env);
    }

    boolean isTestEnv(final String env) {
        return EnvEnum.TEST.getName().equalsIgnoreCase(env);
    }

    boolean isProdEnv() {
        return envEnum.isProdEnv();
    }

    String getProdEnv() {
        return EnvEnum.PROD.getName();
    }

    String getTestEnv() {
        return EnvEnum.TEST.getName();
    }

    String getDevEnv() {
        return EnvEnum.DEV.getName();
    }

    boolean contains(String key) {
        return defaultProperty.containsKey(key);
    }

    private void loadEnvConfig() {
        final String envConfig = defaultProperty.getProperty(ENV_CONFIG);
        if (StringUtils.isBlank(envConfig)) {
            return;
        }

        if (envConfig.endsWith(".yml")) {
            final Map<String, String> ymlConfig = getYml(envConfig);
            if (MapUtils.isNotEmpty(ymlConfig)) {
                defaultProperty.putAll(ymlConfig);
            }
        } else {
            Properties properties = getProperties(envConfig);
            if (MapUtils.isNotEmpty(properties)) {
                defaultProperty.putAll(properties);
            }
        }
    }

    private void loadLocalEnvConfig() {
        String envConfig = System.getenv(LOCAL_ENV_FILE_CONFIG);
        if (StringUtils.isBlank(envConfig)) {
            envConfig = System.getProperty(LOCAL_ENV_FILE_CONFIG);
            if (StringUtils.isBlank(envConfig)) {
                envConfig = ProgramArgumentsEnvironment.get(LOCAL_ENV_FILE_CONFIG);
                if (StringUtils.isBlank(envConfig)) {
                    return;
                }
            }
        }

        String loadFile = null;
        Properties props;
        try {
            loadFile = envConfig;
            // load from /data/env/appenv
            props = PropertiesUtil.loadFromFileSystem(loadFile);
            if (props == null) {
                props = PropertiesUtil.loadFromClassPath(loadFile);
            }
        } catch (Throwable e) {
            props = null;
            System.err.println("failed to load data from " + loadFile);
        }

        if (MapUtils.isNotEmpty(props)) {
            defaultProperty.putAll(props);
        }
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

        //从 program中加载
        value = ProgramArgumentsEnvironment.get(key);
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

        // load from application.properties
        loadFile = APP_EVN_CONFIG_YML;
        final Map<String, String> springBootYml = getYml(loadFile);
        if (MapUtils.isNotEmpty(springBootYml)) {
            springBootYml.remove(KEY_DEPLOY_ENV);
            props.putAll(springBootYml);
        }

        //load springboot config
        loadFile = SPRING_BOOT_CONFIG_YML;
        Properties bootProps = getProperties(loadFile);
        if (null != bootProps && bootProps.size() > 0) {
            props.putAll(bootProps);
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

    private Map<String, String> getYml(final String app) {
        Map<String, String> map = Collections.emptyMap();
        try {
            // load from application.yml
            map = PropertiesUtil.loadYamlFromClassPath(app);
        } catch (Throwable e) {
            System.err.println("failed to load data from " + app);
        }
        return map;
    }

    private Properties getDefaultAppEnv() {
        final Properties props = new Properties();
        //设置发布环境
        if (!defaultProperty.containsKey(KEY_DEPLOY_ENV)) {
            props.put(KEY_DEPLOY_ENV, DEFAULT_DEPLOY_ENV);
        }

        /**
         * get env
         */
        final String env = (String) defaultProperty.get(KEY_DEPLOY_ENV);

        /**
         * 设置config center domain(基于http)
         */
        if (!defaultProperty.containsKey(CONFIG_CENTER)) {
            if (EnvEnum.PROD.getName().equalsIgnoreCase(env)) {
                props.put(CONFIG_CENTER, DEFAULT_ON_LINE_CONFIG_CENTER_URL);
            } else {
                props.put(CONFIG_CENTER, DEFAULT_TEST_CONFIG_CENTER_URL);
            }
        }
        return props;
    }

    public List<String> getPhones() {
        return phones;
    }

    public int getMachineId() {
        return machineId;
    }

    public String getGatewayPath() {
        return gatewayPath;
    }
}