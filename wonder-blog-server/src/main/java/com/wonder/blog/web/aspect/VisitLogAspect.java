package com.wonder.blog.web.aspect;

import com.wonder.blog.cache.redis.VisitCache;
import com.wonder.blog.entity.LogVisitDO;
import com.wonder.blog.entity.VisitUserDO;
import com.wonder.blog.service.LogVisitService;
import com.wonder.blog.service.VisitUserService;
import com.wonder.blog.util.ip.IpAddressUtils;
import com.wonder.blog.util.spring.AopUtils;
import com.wonder.common.annotation.VisitLogger;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Calendar;
import java.util.UUID;

/**
 * @Description: AOP记录访问日志
 * @Author: Yond
 */
@Component
@Aspect
public class VisitLogAspect {
    
    @Resource
    private LogVisitService logVisitService;
    @Resource
    private VisitUserService visitUserService;
    @Resource
    private VisitCache visitCache;
    
    @Pointcut("@annotation(visitLogger)")
    public void logPointcut(VisitLogger visitLogger) {
    }
    
    
    @Around(value = "logPointcut(visitLogger)", argNames = "joinPoint,visitLogger")
    public Object logAround(ProceedingJoinPoint joinPoint, VisitLogger visitLogger) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        int duration = (int) (System.currentTimeMillis() - start);
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        
        String uuid = checkIdentification(request);
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String behavior = visitLogger.value().getBehavior();
        String content = visitLogger.value().getContent();
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String params = StringUtils.substring(AopUtils.getRequestParams(joinPoint), 0, 2000);
        
        Thread.startVirtualThread(() -> {
            handleLog(uuid, uri, method, behavior, content, ip, userAgent, params, duration);
        });
        
        return result;
    }
    
    private void handleLog(String uuid, String uri, String method, String behavior, String content, String ip, String userAgent, String params, int duration) {
        LogVisitDO log = LogVisitDO.custom()
                .setUuid(uuid)
                .setUri(uri)
                .setMethod(method)
                .setBehavior(behavior)
                .setContent(content)
                .setIp(ip)
                .setDuration(duration)
                .setUserAgent(userAgent);
        log.setParam(params);
        logVisitService.saveVisitLog(log);
    }
    
    
    private String checkIdentification(HttpServletRequest request) {
        String identification = request.getHeader("identification");
        if (identification == null) {
            return saveUUID(request);
        }
        boolean redisExist = visitCache.identityExist(identification);
        if (redisExist) {
            return identification;
        }
        boolean mysqlExist = visitUserService.hasUUID(identification);
        if (mysqlExist) {
            visitCache.addIdentity(identification);
        } else {
            identification = saveUUID(request);
        }
        return identification;
    }
    
    private String saveUUID(HttpServletRequest request) {
        //获取响应对象
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //获取当前时间戳，精确到小时，防刷访客数据
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String timestamp = Long.toString(calendar.getTimeInMillis() / 1000);
        //获取访问者基本信息
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        //根据时间戳、ip、userAgent生成UUID
        String nameUUID = timestamp + ip + userAgent;
        String uuid = UUID.nameUUIDFromBytes(nameUUID.getBytes()).toString();
        //添加访客标识码UUID至响应头
        response.addHeader("identification", uuid);
        //暴露自定义header供页面资源使用
        response.addHeader("Access-Control-Expose-Headers", "identification");
        //校验Redis中是否存在uuid
        boolean redisHas = visitCache.identityExist(uuid);
        if (!redisHas) {
            //保存至Redis
            visitCache.addIdentity(uuid);
            //保存至数据库
            VisitUserDO visitor = new VisitUserDO(uuid, ip, userAgent);
            visitUserService.saveVisitor(visitor);
        }
        return uuid;
    }
    
}