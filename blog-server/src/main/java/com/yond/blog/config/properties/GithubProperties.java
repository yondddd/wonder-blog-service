package com.yond.blog.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * GitHub配置(目前用于评论中QQ头像的图床)
 *
 * @Author: Yond
 */
@Configuration
@ConfigurationProperties(prefix = "upload.github")
public class GithubProperties {
    /**
     * GitHub token
     */
    private String token;
    /**
     * GitHub username
     */
    private String username;
    /**
     * GitHub 仓库名
     */
    private String repos;
    /**
     * GitHub 仓库路径
     */
    private String reposPath;

    public GithubProperties() {
    }

    public String getToken() {
        return this.token;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRepos() {
        return this.repos;
    }

    public String getReposPath() {
        return this.reposPath;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    public void setReposPath(String reposPath) {
        this.reposPath = reposPath;
    }

    public String toString() {
        return "GithubProperties(token=" + this.getToken() + ", username=" + this.getUsername() + ", repos=" + this.getRepos() + ", reposPath=" + this.getReposPath() + ")";
    }
}
