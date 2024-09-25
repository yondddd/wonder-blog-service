package com.yond.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Yond
 */
public class StatisticCategoryVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4723103464532555280L;

    /**
     * 分类名集合
     */
    private List<String> legend;
    /**
     * 分类名对应的数量博客数量
     */
    private List<StatisticCategorySeriesVO> series;

    public List<String> getLegend() {
        return legend;
    }

    public StatisticCategoryVO setLegend(List<String> legend) {
        this.legend = legend;
        return this;
    }

    public List<StatisticCategorySeriesVO> getSeries() {
        return series;
    }

    public StatisticCategoryVO setSeries(List<StatisticCategorySeriesVO> series) {
        this.series = series;
        return this;
    }

    @Override
    public String toString() {
        return "StatisticCategoryVO{" +
                "legend=" + legend +
                ", series=" + series +
                '}';
    }
}
