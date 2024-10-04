package com.wish.blog.util.spring;

import com.wish.common.utils.json.util.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: AOP工具类
 * @Author: Yond
 */
public class AopUtils {
    
    public static String getRequestParams(JoinPoint joinPoint) {
        List<Object> data = new ArrayList<>();
        for (Object arg : joinPoint.getArgs()) {
            if (isFilterObject(arg)) {
                continue;
            }
            data.add(arg);
        }
        return JsonUtils.toJsonIgnoreNull(data);
    }
    
    private static boolean isFilterObject(final Object o) {
        return o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof MultipartFile;
    }
    
}
