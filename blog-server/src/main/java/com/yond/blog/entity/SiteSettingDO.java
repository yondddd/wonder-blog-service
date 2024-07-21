package com.yond.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 站点设置
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiteSettingDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 975624987598974165L;
    private Long id;
    private String nameEn;
    private String nameZh;
    private String value;
    private Integer type;

    public SiteSettingDO() {
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

    public String toString() {
        return "SiteSettingDO(id=" + this.getId() + ", nameEn=" + this.getNameEn() + ", nameZh=" + this.getNameZh() + ", value=" + this.getValue() + ", type=" + this.getType() + ")";
    }
}
