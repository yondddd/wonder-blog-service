package com.yond.blog.web.aspect;

import com.yond.blog.entity.ExceptionLogDO;
import com.yond.blog.service.ExceptionLogService;
import com.yond.blog.util.AopUtils;
import com.yond.blog.util.IpAddressUtils;
import com.yond.blog.util.JacksonUtils;
import com.yond.blog.util.MyStringUtils;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.annotation.VisitLogger;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Description: AOP记录异常日志
 * @Author: Naccl
 * @Date: 2020-12-03
 * todo 修改切面日志
 */
//@Component
//@Aspect
public class ExceptionLogAspect {
    @Autowired
    ExceptionLogService exceptionLogService;

    /**
     * 配置切入点
     */
    @Pointcut("execution(* com.yond.blog.web.blog..*.*(..))")
    public void logPointcut() {
    }

    @AfterThrowing(value = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        ExceptionLogDO log = handleLog(joinPoint, e);
        exceptionLogService.saveExceptionLog(log);
    }

    /**
     * 设置ExceptionLog对象属性
     *
     * @return
     */
    private ExceptionLogDO handleLog(JoinPoint joinPoint, Exception e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String ip = IpAddressUtils.getIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String description = getDescriptionFromAnnotations(joinPoint);
        String error = MyStringUtils.getStackTrace(e);
        ExceptionLogDO log = new ExceptionLogDO(uri, method, description, error, ip, userAgent);
        Map<String, Object> requestParams = AopUtils.getRequestParams(joinPoint);
        log.setParam(StringUtils.substring(JacksonUtils.writeValueAsString(requestParams), 0, 2000));
        return log;
    }

    private String getDescriptionFromAnnotations(JoinPoint joinPoint) {
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
