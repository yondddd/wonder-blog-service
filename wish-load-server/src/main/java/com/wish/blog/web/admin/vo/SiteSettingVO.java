package com.wish.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: yond
 */
public class SiteSettingVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4625283143459868022L;

    private String key;
    private String name;
    private String value;
    private Integer type;

    public static SiteSettingVO custom() {
        return new SiteSettingVO();
    }

    public String getKey() {
        return key;
    }

    public SiteSettingVO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public SiteSettingVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public SiteSettingVO setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public SiteSettingVO setType(Integer type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "SiteSettingVO{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
