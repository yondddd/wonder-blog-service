package com.wish.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class StatisticTagSeriesVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4639800565409520575L;

    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public StatisticTagSeriesVO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public StatisticTagSeriesVO setValue(Integer value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "StatisticTagSeriesVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
