package com.yond.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 站点设置
 * @Author: Yond
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiteConfigDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 975624987598974165L;
    private Long id;
    private String nameEn;
    private String nameZh;
    private String value;
    private Integer type;

    public SiteConfigDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public String getNameZh() {
        return this.nameZh;
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

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
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
                ", nameEn='" + nameEn + '\'' +
                ", nameZh='" + nameZh + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
