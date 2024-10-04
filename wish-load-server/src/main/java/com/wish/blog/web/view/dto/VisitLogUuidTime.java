package com.wish.blog.web.view.dto;

import java.util.Date;

/**
 * @Description: 访客更新DTO
 * @Author: Yond
 */
public class VisitLogUuidTime {
    
    private String uuid;
    private Date time;
    private Integer pv;
    
    public VisitLogUuidTime(String uuid, Date time, Integer pv) {
        this.uuid = uuid;
        this.time = time;
        this.pv = pv;
    }
    
    public VisitLogUuidTime() {
    }
    
    public String getUuid() {
        return this.uuid;
    }
    
    public Date getTime() {
        return this.time;
    }
    
    public Integer getPv() {
        return this.pv;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }
    
    public void setPv(Integer pv) {
        this.pv = pv;
    }
    
    public String toString() {
        return "VisitLogUuidTime(uuid=" + this.getUuid() + ", time=" + this.getTime() + ", pv=" + this.getPv() + ")";
    }
}
