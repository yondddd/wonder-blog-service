package com.yond.blog.web.aspect;

import com.yond.blog.entity.ExceptionLogDO;
import com.yond.blog.service.ExceptionLogService;
import com.yond.blog.util.AopUtils;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.MyStringUtils;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.annotation.VisitLogger;
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
 * @Author: Naccl
 * @Date: 2020-12-03
 * todo 修改切面日志
 */
@Component
@Aspect
public class ExceptionLogAspect {
    
    @Autowired
    ExceptionLogService exceptionLogService;
    
    @Pointcut("execution(* com.yond.blog.web.blog.admin.controller..*.*(..)) || execution(* com.yond.blog.web.blog.view.controller..*.*(..))")
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
        String error = MyStringUtils.getStackTrace(e);
        
        Thread.startVirtualThread(() -> {
            handleLog(uri, method, ip, userAgent, description, error, params);
        });
    }
    
    private void handleLog(String uri, String method, String ip, String userAgent, String description, String error, String params) {
        ExceptionLogDO log = ExceptionLogDO.custom()
                .setUri(uri)
                .setMethod(method)
                .setIp(ip)
                .setUserAgent(userAgent)
                .setDescription(description)
                .setError(error)
                .setParam(params);
        exceptionLogService.saveExceptionLog(log);
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
