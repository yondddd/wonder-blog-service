package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 博客动态
 * @Author: Naccl
 * @Date: 2020-08-24
 */
public class MomentDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7053603743312052909L;

    private Long id;
    private String content;//动态内容
    private Date createTime;//创建时间
    private Integer likes;//点赞数量
    private Boolean published;//是否公开

    public MomentDO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Integer getLikes() {
        return this.likes;
    }

    public Boolean getPublished() {
        return this.published;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String toString() {
        return "MomentDO(id=" + this.getId() + ", content=" + this.getContent() + ", createTime=" + this.getCreateTime() + ", likes=" + this.getLikes() + ", published=" + this.getPublished() + ")";
    }
}
