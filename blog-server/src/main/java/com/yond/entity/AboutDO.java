package com.yond.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 关于我
 * @Author: Naccl
 * @Date: 2020-08-31
 */
public class AboutDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7160388998935748571L;

    private Long id;
    private String nameEn;
    private String nameZh;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AboutDO{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameZh='" + nameZh + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
