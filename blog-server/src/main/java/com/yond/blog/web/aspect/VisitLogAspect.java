package com.yond.blog.web.aspect;

import com.yond.blog.cache.remote.VisitCache;
import com.yond.blog.entity.VisitLogDO;
import com.yond.blog.entity.VisitorDO;
import com.yond.blog.service.VisitLogService;
import com.yond.blog.service.VisitorService;
import com.yond.blog.util.AopUtils;
import com.yond.blog.util.IpAddressUtils;
import com.yond.common.annotation.VisitLogger;
import com.yond.common.enums.VisitBehavior;
import com.yond.common.utils.json.util.JsonUtils;
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
 * @Author: Naccl
 * @Date: 2020-12-04
 */
@Component
@Aspect
public class VisitLogAspect {
    
    @Resource
    private VisitLogService visitLogService;
    @Resource
    private VisitorService visitorService;
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
        Thread.startVirtualThread(() -> {
            handleLog(joinPoint, visitLogger, request, duration);
        });
        return result;
    }
    
    
    private void handleLog(ProceedingJoinPoint joinPoint, VisitLogger visitLogger, HttpServletRequest request,
                           int duration) {
        
        VisitBehavior visitBehavior = visitLogger.value();
        VisitLogDO log = VisitLogDO.custom()
                .setUuid(checkIdentification(request))
                .setUri(request.getRequestURI())
                .setMethod(request.getMethod())
                .setBehavior(visitBehavior.getBehavior())
                .setContent(visitBehavior.getContent())
                .setIp(IpAddressUtils.getIpAddress(request))
                .setTimes(duration)
                .setUserAgent(request.getHeader("User-Agent"));
        log.setParam(StringUtils.substring(JsonUtils.toJson(AopUtils.getRequestParams(joinPoint)), 0, 2000));
        visitLogService.saveVisitLog(log);
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
        boolean mysqlExist = visitorService.hasUUID(identification);
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
            VisitorDO visitor = new VisitorDO(uuid, ip, userAgent);
            visitorService.saveVisitor(visitor);
        }
        return uuid;
    }
    
}