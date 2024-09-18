package com.yond.blog.config;

import com.yond.blog.config.properties.ProxyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * RestTemplate相关的Bean配置
 *
 * @Author: Yond
 */
@Configuration
public class RestTemplateConfig {
    @Autowired
    private ProxyProperties proxyProperties;

    /**
     * 默认的RestTemplate
     *
     * @return
     */
    @Bean
    @Primary
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 配置了代理和超时时间的RestTemplate
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplateByProxy() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProperties.getHost(), proxyProperties.getPort()));
        requestFactory.setProxy(proxy);
        requestFactory.setConnectTimeout(proxyProperties.getTimeout());
        return new RestTemplate(requestFactory);
    }
}