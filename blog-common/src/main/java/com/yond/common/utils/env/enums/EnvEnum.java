package com.yond.common.utils.env.enums;

public enum EnvEnum {

    DEV(10, "dev"),

    TEST(50, "test"),

    PROD(100, "prod");

    private final int code;

    private final String name;

    EnvEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static EnvEnum getEnvEnum(final String env) {
        EnvEnum[] envEnums = EnvEnum.values();
        for (int i = 0, size = envEnums.length; i < size; i++) {
            if (envEnums[i].getName().equalsIgnoreCase(env)) {
                return envEnums[i];
            }
        }

        return DEV;
    }

    public boolean isDevEnv() {
        return code == DEV.code;
    }


    public boolean isProdEnv() {
        return code == PROD.code;
    }
}
