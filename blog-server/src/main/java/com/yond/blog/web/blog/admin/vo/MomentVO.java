package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yond
 * @date 8/31/2024
 * @description
 */
public class MomentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3611837352317752392L;

    private Long id;
    private String content;
    private Integer likes;
    private Boolean published;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public MomentVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MomentVO setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public MomentVO setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public MomentVO setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MomentVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "MomentVO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", published=" + published +
                ", createTime=" + createTime +
                '}';
    }
}
