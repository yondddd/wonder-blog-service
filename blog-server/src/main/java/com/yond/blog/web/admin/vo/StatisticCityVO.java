package com.yond.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class StatisticCityVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 445302174718968032L;

    private String city;
    private Integer uv;

    public String getCity() {
        return city;
    }

    public StatisticCityVO setCity(String city) {
        this.city = city;
        return this;
    }

    public Integer getUv() {
        return uv;
    }

    public StatisticCityVO setUv(Integer uv) {
        this.uv = uv;
        return this;
    }

    @Override
    public String toString() {
        return "StatisticCityVO{" +
                "city='" + city + '\'' +
                ", uv=" + uv +
                '}';
    }
}
