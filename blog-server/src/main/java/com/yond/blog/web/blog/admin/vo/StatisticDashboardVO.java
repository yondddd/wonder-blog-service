package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 仪表盘
 * @Author: WangJieLong
 * @Date: 2024-09-14
 */
public class StatisticDashboardVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 5181010656045399830L;
    
    private Integer pv;
    private Integer uv;
    private Integer blogCount;
    private Integer commentCount;
    private StatisticCategoryVO category;
    private StatisticTagVO tag;
    private List<StatisticCityVO> cityVisitor;
    private StatisticVisitRecordVO visitRecord;
    
    public Integer getPv() {
        return pv;
    }
    
    public StatisticDashboardVO setPv(Integer pv) {
        this.pv = pv;
        return this;
    }
    
    public Integer getUv() {
        return uv;
    }
    
    public StatisticDashboardVO setUv(Integer uv) {
        this.uv = uv;
        return this;
    }
    
    public Integer getBlogCount() {
        return blogCount;
    }
    
    public StatisticDashboardVO setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
        return this;
    }
    
    public Integer getCommentCount() {
        return commentCount;
    }
    
    public StatisticDashboardVO setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }
    
    public StatisticCategoryVO getCategory() {
        return category;
    }
    
    public StatisticDashboardVO setCategory(StatisticCategoryVO category) {
        this.category = category;
        return this;
    }
    
    public StatisticTagVO getTag() {
        return tag;
    }
    
    public StatisticDashboardVO setTag(StatisticTagVO tag) {
        this.tag = tag;
        return this;
    }
    
    public List<StatisticCityVO> getCityVisitor() {
        return cityVisitor;
    }
    
    public StatisticDashboardVO setCityVisitor(List<StatisticCityVO> cityVisitor) {
        this.cityVisitor = cityVisitor;
        return this;
    }
    
    public StatisticVisitRecordVO getVisitRecord() {
        return visitRecord;
    }
    
    public StatisticDashboardVO setVisitRecord(StatisticVisitRecordVO visitRecord) {
        this.visitRecord = visitRecord;
        return this;
    }
    
    @Override
    public String toString() {
        return "StatisticDashboardVO{" +
                "pv=" + pv +
                ", uv=" + uv +
                ", blogCount=" + blogCount +
                ", commentCount=" + commentCount +
                ", category=" + category +
                ", tag=" + tag +
                ", cityVisitor=" + cityVisitor +
                ", visitRecord=" + visitRecord +
                '}';
    }
}
