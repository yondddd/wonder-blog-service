package com.wonder.blog.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 静态文件上传访问路径配置(目前用于评论中QQ头像的本地存储)
 *
 * @Author: Yond
 */
@Configuration
@ConfigurationProperties(prefix = "upload.file")
public class UploadProperties {
    /**
     * 本地文件路径
     */
    private String path;
    /**
     * 请求地址映射
     */
    private String accessPath;
    /**
     * 本地文件路径映射
     */
    private String resourcesLocations;

    public UploadProperties() {
    }

    public String getPath() {
        return this.path;
    }

    public String getAccessPath() {
        return this.accessPath;
    }

    public String getResourcesLocations() {
        return this.resourcesLocations;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public void setResourcesLocations(String resourcesLocations) {
        this.resourcesLocations = resourcesLocations;
    }

    public String toString() {
        return "UploadProperties(path=" + this.getPath() + ", accessPath=" + this.getAccessPath() + ", resourcesLocations=" + this.getResourcesLocations() + ")";
    }
}
