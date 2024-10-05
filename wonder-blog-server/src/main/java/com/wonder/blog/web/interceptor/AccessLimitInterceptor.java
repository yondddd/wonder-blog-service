package com.wonder.blog.web.interceptor;

import com.wonder.blog.cache.redis.AccessLimitCache;
import com.wonder.blog.util.ip.IpAddressUtils;
import com.wonder.blog.util.web.WebFilterUtil;
import com.wonder.common.annotation.AccessLimit;
import com.wonder.common.resp.Response;
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
        Long current = accessLimitCache.incrBy(key, 1, seconds);
        if (current <= maxCount) {
            return true;
        }
        WebFilterUtil.returnFail(response, HttpStatus.SC_FORBIDDEN, Response.custom(403, accessLimit.msg()));
        return false;
    }
    
}
