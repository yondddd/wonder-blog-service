package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Yond
 */
public class StatisticCategorySeriesVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1828917295101484742L;

    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public StatisticCategorySeriesVO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public StatisticCategorySeriesVO setValue(Integer value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "StatisticCategorySeriesVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
