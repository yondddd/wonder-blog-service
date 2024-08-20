package com.yond.blog.web.blog.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 8/20/2024
 * @description
 */
public class TagVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4288642746044002755L;

    private Long tagId;
    private String tagName;
    private String color;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TagVO{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
