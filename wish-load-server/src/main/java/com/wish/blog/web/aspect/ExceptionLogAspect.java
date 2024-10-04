package com.wish.blog.web.aspect;

import com.wish.blog.entity.LogExceptionDO;
import com.wish.blog.service.LogExceptionService;
import com.wish.blog.util.ip.IpAddressUtils;
import com.wish.blog.util.spring.AopUtils;
import com.wish.common.annotation.OperationLogger;
import com.wish.common.annotation.VisitLogger;
import com.wish.common.exception.ExceptionStackUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * @Description: AOP记录异常日志
 * @Author: Yond
 * todo 修改切面日志
 */
@Component
@Aspect
public class ExceptionLogAspect {
    
    @Autowired
    LogExceptionService logExceptionService;
    
    @Pointcut("execution(* com.wish.blog.web.view.admin.controller..*.*(..)) || execution(* com.wish.blog.web.view.view.controller..*.*(..))")
    public void logPointcut() {
    }
    
    
    @AfterThrowing(value = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String params = StringUtils.substring(AopUtils.getRequestParams(joinPoint), 0, 2000);
        String description = getOperate(joinPoint);
        String error = ExceptionStackUtil.getStackTrace(e);
        
        Thread.startVirtualThread(() -> {
            handleLog(uri, method, ip, userAgent, description, error, params);
        });
    }
    
    private void handleLog(String uri, String method, String ip, String userAgent, String description, String error, String params) {
        LogExceptionDO log = LogExceptionDO.custom()
                .setUri(uri)
                .setMethod(method)
                .setIp(ip)
                .setUserAgent(userAgent)
                .setDescription(description)
                .setError(error)
                .setParam(params);
        logExceptionService.saveExceptionLog(log);
    }
    
    private String getOperate(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        OperationLogger operationLogger = method.getAnnotation(OperationLogger.class);
        if (operationLogger != null) {
            return operationLogger.value();
        }
        VisitLogger visitLogger = method.getAnnotation(VisitLogger.class);
        if (visitLogger != null) {
            return visitLogger.value().getBehavior();
        }
        return "";
    }
    
    
}
