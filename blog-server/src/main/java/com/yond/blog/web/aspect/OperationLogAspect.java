package com.yond.blog.web.aspect;

import com.yond.blog.entity.LogOperationDO;
import com.yond.blog.service.LogOperationService;
import com.yond.blog.util.AopUtils;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.constant.JwtConstant;
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
 * @Author: Yond
 */
@Component
@Aspect
public class OperationLogAspect {

    @Resource
    private LogOperationService logOperationService;

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
        String token = request.getHeader(JwtConstant.TOKEN_HEADER);
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String params = StringUtils.substring(AopUtils.getRequestParams(joinPoint), 0, 2000);
        String username = JwtUtil.validateJwt(token, JwtConstant.DEFAULT_SECRET).getSubject();

        Thread.startVirtualThread(() -> {
            handleLog(username, uri, method, ip, userAgent, duration, params, operationLogger);
        });

        return result;
    }

    private void handleLog(String username, String uri, String method, String ip, String userAgent, int duration, String params, OperationLogger operationLogger) {
        LogOperationDO log = LogOperationDO.custom()
                .setUsername(username)
                .setUri(uri)
                .setMethod(method)
                .setDescription(operationLogger.value())
                .setIp(ip)
                .setTimes(duration)
                .setUserAgent(userAgent);
        log.setParam(params);
        logOperationService.saveOperationLog(log);
    }


}