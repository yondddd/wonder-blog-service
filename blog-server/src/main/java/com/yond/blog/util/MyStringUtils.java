package com.yond.blog.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description: 字符串校验
 * @Author: Yond
 */
public class MyStringUtils {

    /**
     * 判断字符串中是否包含特殊字符
     *
     * @param str 待校验字符串
     * @return
     */
    public static boolean hasSpecialChar(String... str) {
        for (String s : str) {
            if (s.contains("%") || s.contains("_") || s.contains("[") || s.contains("#") || s.contains("*")) {
                return true;
            }
        }
        return false;
    }

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
