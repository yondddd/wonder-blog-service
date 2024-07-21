package com.yond.blog.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Telegram配置
 *
 * @author: Naccl
 * @date: 2022-01-22
 */
@Configuration
@ConfigurationProperties(prefix = "tg.bot")
public class TelegramProperties {
    /**
     * Telegram bot的api，默认是https://api.telegram.org/bot
     * 如果使用自己的反代，可以修改它
     */
    private String api;
    /**
     * bot的token，可以从 @BotFather 处获取
     */
    private String token;
    /**
     * 自己账号和bot的聊天会话id
     */
    private String chatId;
    /**
     * 是否使用代理
     */
    private Boolean useProxy;
    /**
     * 是否使用反向代理
     */
    private Boolean useReverseProxy;
    /**
     * 反向代理URL
     */
    private String reverseProxyUrl;

    public TelegramProperties() {
    }

    public String getApi() {
        return this.api;
    }

    public String getToken() {
        return this.token;
    }

    public String getChatId() {
        return this.chatId;
    }

    public Boolean getUseProxy() {
        return this.useProxy;
    }

    public Boolean getUseReverseProxy() {
        return this.useReverseProxy;
    }

    public String getReverseProxyUrl() {
        return this.reverseProxyUrl;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setUseProxy(Boolean useProxy) {
        this.useProxy = useProxy;
    }

    public void setUseReverseProxy(Boolean useReverseProxy) {
        this.useReverseProxy = useReverseProxy;
    }

    public void setReverseProxyUrl(String reverseProxyUrl) {
        this.reverseProxyUrl = reverseProxyUrl;
    }

    public String toString() {
        return "TelegramProperties(api=" + this.getApi() + ", token=" + this.getToken() + ", chatId=" + this.getChatId() + ", useProxy=" + this.getUseProxy() + ", useReverseProxy=" + this.getUseReverseProxy() + ", reverseProxyUrl=" + this.getReverseProxyUrl() + ")";
    }
}
