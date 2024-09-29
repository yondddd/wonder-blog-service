package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 站点设置
 * @Author: Yond
 */
public class SiteConfigDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 975624987598974165L;

    private Long id;
    private String key;
    private String name;
    private String value;
    private Integer type;

    public SiteConfigDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getType() {
        return this.type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SiteSettingDO{" +
                "id=" + id +
                ", nameEn='" + key + '\'' +
                ", nameZh='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
