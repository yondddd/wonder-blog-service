package com.yond.blog.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * 代理配置(目前用于RestTemplate发送tg消息)
 *
 * @author: Naccl
 * @date: 2022-01-22
 */
@Configuration
@ConfigurationProperties(prefix = "http.proxy.server")
public class ProxyProperties {
    /**
     * 代理服务器地址
     */
    private String host;
    /**
     * 代理服务器端口
     */
    private Integer port;
    /**
     * 连接超时(单位毫秒)，通常不应该为0，0为无限超时时间，-1为系统的默认超时时间
     *
     * @see SimpleClientHttpRequestFactory#setConnectTimeout(int)
     */
    private Integer timeout;

    public ProxyProperties() {
    }

    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String toString() {
        return "ProxyProperties(host=" + this.getHost() + ", port=" + this.getPort() + ", timeout=" + this.getTimeout() + ")";
    }
}
