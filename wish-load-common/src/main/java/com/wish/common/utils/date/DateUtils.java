package com.wish.common.utils.date;


import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class DateUtils {
    
    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String MONTH_FORMAT = "yyyy年MM月";
    public static final String DAY_FORMAT = "dd日";
    
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

