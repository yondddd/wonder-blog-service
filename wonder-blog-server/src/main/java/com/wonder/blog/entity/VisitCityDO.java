package com.wonder.blog.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 城市访客数量
 * @Author: Yond
 */
public class VisitCityDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4952990742645825000L;

    private Long id;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 独立访客数量
     */
    private Integer uv;

    public VisitCityDO(String city, Integer uv) {
        this.city = city;
        this.uv = uv;
    }

    public VisitCityDO() {
    }

    public Long getId() {
        return id;
    }

    public VisitCityDO setId(Long id) {
        this.id = id;
        return this;
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
                "id=" + id +
                ", city='" + city + '\'' +
                ", uv=" + uv +
                '}';
    }
}
