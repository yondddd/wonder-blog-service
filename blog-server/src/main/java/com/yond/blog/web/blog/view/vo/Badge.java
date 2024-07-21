package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: GitHub徽标
 * @Author: Naccl
 * @Date: 2020-08-09
 */
public class Badge implements Serializable {
    @Serial
    private static final long serialVersionUID = 2470572916576310844L;
    private String title;
    private String url;
    private String subject;
    private String value;
    private String color;

    public Badge() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getValue() {
        return this.value;
    }

    public String getColor() {
        return this.color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return "Badge(title=" + this.getTitle() + ", url=" + this.getUrl() + ", subject=" + this.getSubject() + ", value=" + this.getValue() + ", color=" + this.getColor() + ")";
    }
}
