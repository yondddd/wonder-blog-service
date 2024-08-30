package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: WangJieLong
 * @Date: 2024-08-30
 */
public class SiteSettingVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 4625283143459868022L;
    
    private Long id;
    private String nameEn;
    private String nameZh;
    private String value;
    private Integer type;
    
    public static SiteSettingVO custom() {
        return new SiteSettingVO();
    }
    
    public Long getId() {
        return id;
    }
    
    public SiteSettingVO setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getNameEn() {
        return nameEn;
    }
    
    public SiteSettingVO setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }
    
    public String getNameZh() {
        return nameZh;
    }
    
    public SiteSettingVO setNameZh(String nameZh) {
        this.nameZh = nameZh;
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
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameZh='" + nameZh + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
