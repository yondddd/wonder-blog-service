package com.yond.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate相关的Bean配置
 *
 * @Author: Yond
 */
@Configuration
public class RestTemplateConfig {
    
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
        return new RestTemplate(requestFactory);
    }
    
}