package com.yond.blog.web.interceptor;

import com.yond.blog.cache.redis.AccessLimitCache;
import com.yond.blog.util.ip.IpAddressUtils;
import com.yond.blog.util.web.WebFilterUtil;
import com.yond.common.annotation.AccessLimit;
import com.yond.common.resp.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description: 限流
 * @Author: Yond
 */
@Component
public class AccessLimitInterceptor implements HandlerInterceptor {
    
    @Resource
    private AccessLimitCache accessLimitCache;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean process = handler instanceof HandlerMethod;
        if (!process) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
        if (accessLimit == null) {
            return true;
        }
        int seconds = accessLimit.seconds();
        int maxCount = accessLimit.maxCount();
        String ip = IpAddressUtils.getIpAddress(request);
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String key = ip + ":" + method + ":" + requestURI;
        Integer count = accessLimitCache.getCount(key);
        if (count == null) {
            accessLimitCache.incrBy(key, 1, seconds);
            return true;
        }
        if (count < maxCount) {
            accessLimitCache.increment(key, 1);
            return true;
        }
        WebFilterUtil.returnFail(response, HttpStatus.SC_FORBIDDEN, Response.custom(403, accessLimit.msg()));
        return false;
    }
    
}
