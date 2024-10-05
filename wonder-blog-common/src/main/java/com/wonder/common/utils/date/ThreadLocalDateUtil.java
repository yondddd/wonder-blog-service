package com.wonder.common.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDateUtil {

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {

        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };

    private static final ThreadLocal<DateFormat> simple_threadLocal = new ThreadLocal<DateFormat>() {

        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        }
    };

    public static DateFormat getDateFormat() {
        return threadLocal.get();
    }

    public static DateFormat getSimpleDateFormat() {
        return simple_threadLocal.get();
    }

    public static Date parse(String value) {
        try {
            return getDateFormat().parse(value);
        } catch (Exception ex) {
            //quite
        }
        return null;
    }

    public static Date simpleParse(String value) {
        if (null == value) {
            return null;
        }
        try {
            return getSimpleDateFormat().parse(value);
        } catch (Exception ex) {
            //quite
        }
        return null;
    }

    public static String format(Date date) {
        return getDateFormat().format(date);
    }
}
