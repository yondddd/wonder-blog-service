package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 城市访客数量
 * @Author: Naccl
 * @Date: 2021-02-26
 */
public class CityVisitorDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4952990742645825000L;

    /**
     * 城市名称
     */
    private String city;
    /**
     * 独立访客数量
     */
    private Integer uv;

    public CityVisitorDO(String city, Integer uv) {
        this.city = city;
        this.uv = uv;
    }

    public CityVisitorDO() {
    }

    public String getCity() {
        return this.city;
    }

    public Integer getUv() {
        return this.uv;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    @Override
    public String toString() {
        return "CityVisitorDO{" +
                "city='" + city + '\'' +
                ", uv=" + uv +
                '}';
    }
}
