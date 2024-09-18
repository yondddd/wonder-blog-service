package com.yond.blog.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 博客动态
 * @Author: Yond
 */
public class MomentDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7053603743312052909L;

    private Long id;
    private String content;//动态内容
    private Integer likes;//点赞数量
    private Boolean published;//是否公开
    private Integer status;
    private Date createTime;//创建时间

    public static MomentDO custom() {
        return new MomentDO();
    }

    public MomentDO() {
    }

    public Long getId() {
        return id;
    }

    public MomentDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MomentDO setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public MomentDO setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public MomentDO setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public MomentDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MomentDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "MomentDO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", published=" + published +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
