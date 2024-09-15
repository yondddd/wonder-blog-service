package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Yond
 * @Date: 2024-09-14
 */
public class StatisticVisitRecordVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7409025390216796369L;
    /**
     * 日期 08-15
     */
    private List<String> date;
    private List<Integer> pv;
    private List<Integer> uv;

    public List<String> getDate() {
        return date;
    }

    public StatisticVisitRecordVO setDate(List<String> date) {
        this.date = date;
        return this;
    }

    public List<Integer> getPv() {
        return pv;
    }

    public StatisticVisitRecordVO setPv(List<Integer> pv) {
        this.pv = pv;
        return this;
    }

    public List<Integer> getUv() {
        return uv;
    }

    public StatisticVisitRecordVO setUv(List<Integer> uv) {
        this.uv = uv;
        return this;
    }

    @Override
    public String toString() {
        return "StatisticVisitRecordVO{" +
                "date=" + date +
                ", pv=" + pv +
                ", uv=" + uv +
                '}';
    }
}
