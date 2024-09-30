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
        return id;
    }

    public SiteConfigDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public SiteConfigDO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public SiteConfigDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public SiteConfigDO setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public SiteConfigDO setType(Integer type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "SiteConfigDO{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
