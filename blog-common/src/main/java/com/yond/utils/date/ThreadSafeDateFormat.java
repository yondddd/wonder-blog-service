package com.yond.utils.date;

import com.yond.utils.api.Factory;
import org.apache.commons.pool2.ObjectPool;

import java.text.ParseException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * SimpleDateFormat对象池
 * <p>
 * 解决SimpleDateFormat线程不安全问题
 **/
public class ThreadSafeDateFormat {

    private final String format;

    private final ObjectPool<DateFormat> pool;

    public ThreadSafeDateFormat(final String format, final int initialPoolSize, final int maxPoolSize) {
        this.format = format;
        this.pool = createPool(initialPoolSize, maxPoolSize);
    }

    private SimplePool createPool(int initialPoolSize, int maxPoolSize) {
        return new SimplePool(initialPoolSize, maxPoolSize, new DefaultFactory());
    }

    public static ThreadSafeDateFormat of(String format, int initialPoolSize, int maxPoolSize) {
        return new ThreadSafeDateFormat(format, initialPoolSize, maxPoolSize);
    }

    public String format(Date date) {
        DateFormat format = null;
        try {
            format = fetchFromPool();
            return format.format(date);
        } catch (NoSuchElementException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), Optional.ofNullable(ex.getCause()).orElse(ex));
        } finally {
            try {
                pool.returnObject(format);
            } catch (Exception ex) {
                //quite
            }
        }
    }

    public Date parse(final String date) {
        DateFormat format = null;
        try {
            format = fetchFromPool();
            return format.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("ParseException occur exception", Optional.ofNullable(ex.getCause()).orElse(ex));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), Optional.ofNullable(ex.getCause()).orElse(ex));
        } finally {
            try {
                pool.returnObject(format);
            } catch (Exception ex) {
                //quite
            }
        }
    }

    private DateFormat fetchFromPool() throws Exception {
        return pool.borrowObject();
    }

    @Override
    public String toString() {
        return format;
    }

    private class DefaultFactory implements Factory<DateFormat> {

        @Override
        public DateFormat newInstance() {
            return new DateFormat(format);
        }
    }
}
