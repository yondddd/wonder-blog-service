package com.yond.blog.config;

import com.yond.blog.config.properties.UploadProperties;
import com.yond.blog.web.handler.CurrentUserResolver;
import com.yond.blog.web.interceptor.AccessLimitInterceptor;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description: 配置CORS跨域支持、拦截器
 * @Author: Yond
 * @Date: 2020-07-22
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private AccessLimitInterceptor accessLimitInterceptor;
    @Resource
    private UploadProperties uploadProperties;
    @Resource
    private CurrentUserResolver currentUserResolver;

    /**
     * 跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .maxAge(3600);
    }

    /**
     * 请求拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitInterceptor);
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                if (!"GET".equalsIgnoreCase(request.getMethod()) || !request.getRequestURI().equals("/favicon.ico")) {
                    return true;
                }
                response.setStatus(HttpStatus.NO_CONTENT.value()); // 设置状态码为204 No Content
                return false;
            }
        }).addPathPatterns("/**");
    }

    /**
     * 本地静态资源路径映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadProperties.getAccessPath()).addResourceLocations(uploadProperties.getResourcesLocations());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserResolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

}
