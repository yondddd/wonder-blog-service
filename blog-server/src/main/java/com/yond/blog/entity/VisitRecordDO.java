package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 访问记录
 * @Author: Naccl
 * @Date: 2021-02-23
 */
public class VisitRecordDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8699975183423734099L;
    private Long id;
    private Integer pv;//访问量
    private Integer uv;//独立用户
    private String date;//日期"02-23"

    public VisitRecordDO(Integer pv, Integer uv, String date) {
        this.pv = pv;
        this.uv = uv;
        this.date = date;
    }

    public VisitRecordDO() {
    }

    public Long getId() {
        return this.id;
    }

    public Integer getPv() {
        return this.pv;
    }

    public Integer getUv() {
        return this.uv;
    }

    public String getDate() {
        return this.date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return "VisitRecordDO(id=" + this.getId() + ", pv=" + this.getPv() + ", uv=" + this.getUv() + ", date=" + this.getDate() + ")";
    }
}
