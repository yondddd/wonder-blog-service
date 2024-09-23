package com.yond.blog.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description: 字符串校验
 * @Author: Yond
 */
public class MyStringUtils {
    
    /**
     * 获取堆栈信息
     *
     * @param throwable 异常
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter(1024);
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
    
}
