package com.yond.utils.date;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 日期处理工具类
 * 注：由于 SimpleDateFormat是线程不安全，所以将其设计成线程安全
 *
 * @version 1.0
 * @created 2017/3/31 16:15
 * @see SimpleDateFormat#format(Date)
 * @see Date#parse(String)
 **/
public final class DateUtils {

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final AtomicLong COUNTER = new AtomicLong(0);

    private static final Map<String, ThreadSafeDateFormat> DATA_FORMAT_CONVERT_POOL_MAP = new ConcurrentHashMap<String, ThreadSafeDateFormat>();

    public static String format(String format, Date date) {
        if (null == date) {
            return null;
        }

        ThreadSafeDateFormat dateFormat = getDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date parse(String format, String date) {
        if (null == date) {
            return null;
        }

        ThreadSafeDateFormat dateFormat = getDateFormat(format);
        return dateFormat.parse(date);
    }

    public static Date parse(String date) {
        return DateFormatSupport.parse(date);
    }

    private static ThreadSafeDateFormat getDateFormat(String format) {
        ThreadSafeDateFormat pool = DATA_FORMAT_CONVERT_POOL_MAP.get(format);
        if (null == pool) {
            pool = createThreadSafeDateFormat(format);
        }
        return pool;
    }

    private static ThreadSafeDateFormat createThreadSafeDateFormat(final String format) {
        ThreadSafeDateFormat safeDateFormat;
        while (true) {
            safeDateFormat = DATA_FORMAT_CONVERT_POOL_MAP.get(format);
            if (null != safeDateFormat) {
                break;
            }

            long count = COUNTER.get();

            if (COUNTER.compareAndSet(count, count + 1)) {
                safeDateFormat = ThreadSafeDateFormat.of(format, 10, 30);
                DATA_FORMAT_CONVERT_POOL_MAP.put(format, safeDateFormat);
                break;
            }
        }
        return safeDateFormat;
    }
}

