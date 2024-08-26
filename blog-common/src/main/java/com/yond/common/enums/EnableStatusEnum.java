package com.yond.common.enums;

/**
 * @author yond
 * @date 8/25/2024
 * @description
 */
public enum EnableStatusEnum {

    ENABLE(1, "正常"),
    DELETE(0, "删除"),
    ;

    private final Integer val;
    private final String desc;

    EnableStatusEnum(Integer val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    public Integer getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }

    public static EnableStatusEnum getByVal(Integer val) {
        for (EnableStatusEnum e : EnableStatusEnum.values()) {
            if (e.val.equals(val)) {
                return e;
            }
        }
        throw new RuntimeException("匹配不到的枚举值：" + val);
    }

}
