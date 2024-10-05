package com.wonder.blog.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 又拍云配置(目前用于评论中QQ头像的图床)
 *
 * @Author: Yond
 */
@Configuration
@ConfigurationProperties(prefix = "upload.upyun")
public class UpyunProperties {
    /**
     * 又拍云存储空间名称
     */
    private String bucketName;
    /**
     * 操作员名称
     */
    private String username;
    /**
     * 操作员密码
     */
    private String password;
    /**
     * 存储路径
     */
    private String path;
    /**
     * CDN访问域名
     */
    private String domain;

    public UpyunProperties() {
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPath() {
        return this.path;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String toString() {
        return "UpyunProperties(bucketName=" + this.getBucketName() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", path=" + this.getPath() + ", domain=" + this.getDomain() + ")";
    }
}
