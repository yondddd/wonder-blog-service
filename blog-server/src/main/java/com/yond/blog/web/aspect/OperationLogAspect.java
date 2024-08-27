package com.yond.blog.web.aspect;

import com.yond.blog.entity.OperationLogDO;
import com.yond.blog.service.OperationLogService;
import com.yond.blog.util.AopUtils;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.constant.JwtConstant;
import com.yond.common.utils.json.util.JsonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description: AOP记录操作日志
 * @Author: Naccl
 * @Date: 2020-11-29
 */
@Component
@Aspect
public class OperationLogAspect {
    
    @Resource
    private OperationLogService operationLogService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogAspect.class);
    
    @Pointcut("@annotation(operationLogger)")
    public void logPointcut(OperationLogger operationLogger) {
    }
    
    @Around(value = "logPointcut(operationLogger)", argNames = "joinPoint,operationLogger")
    public Object logAround(ProceedingJoinPoint joinPoint, OperationLogger operationLogger) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        int duration = (int) (System.currentTimeMillis() - start);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Thread.startVirtualThread(() -> {
            handleLog(joinPoint, operationLogger, duration, request);
        });
        return result;
    }
    
    private void handleLog(ProceedingJoinPoint joinPoint, OperationLogger operationLogger, int duration, HttpServletRequest request) {
        OperationLogDO log = OperationLogDO.custom()
                .setUsername(JwtUtil.validateJwt(request.getHeader(JwtConstant.TOKEN_HEADER), JwtConstant.DEFAULT_SECRET).getSubject())
                .setUri(request.getRequestURI())
                .setMethod(request.getMethod())
                .setDescription(operationLogger.value())
                .setIp(IpAddressUtils.getIpAddress(request))
                .setTimes(duration)
                .setUserAgent(request.getHeader("User-Agent"));
        log.setParam(StringUtils.substring(JsonUtils.toJson(AopUtils.getRequestParams(joinPoint)), 0, 2000));
        operationLogService.saveOperationLog(log);
    }
    
}