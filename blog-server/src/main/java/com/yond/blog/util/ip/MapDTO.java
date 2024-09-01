package com.yond.blog.util.ip;

/**
 * @Description: 地图
 * @Author: yond
 * @Date: 2023-05-29
 */
public class MapDTO {

    /**
     * 国家
     */
    private String country;
    /**
     * 城市
     */
    private String city;
    /**
     * 经度
     */
    private String lon;
    /**
     * 维度
     */
    private String lat;
    /**
     * 访问量
     */
    private Integer count;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
