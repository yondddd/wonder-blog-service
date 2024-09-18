package com.yond.blog.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 博客配置(目前用于评论提醒模板中的超链接)
 *
 * @Author: Yond
 */
@Configuration
@ConfigurationProperties(prefix = "blog")
public class BlogProperties {
    /**
     * 博客名称
     */
    private String name;
    /**
     * 博客后端接口URL
     */
    private String api;
    /**
     * 博客前端后台管理URL
     */
    private String cms;
    /**
     * 博客前端前台页面URL
     */
    private String view;

    public BlogProperties() {
    }

    public String getName() {
        return this.name;
    }

    public String getApi() {
        return this.api;
    }

    public String getCms() {
        return this.cms;
    }

    public String getView() {
        return this.view;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setCms(String cms) {
        this.cms = cms;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String toString() {
        return "BlogProperties(name=" + this.getName() + ", api=" + this.getApi() + ", cms=" + this.getCms() + ", view=" + this.getView() + ")";
    }
}
