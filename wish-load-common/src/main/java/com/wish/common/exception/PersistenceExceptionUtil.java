package com.wish.common.exception;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * 持久化异常工具
 */
public class PersistenceExceptionUtil {

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new PersistenceException(message);
        }
    }

    public static void notBlank(String string, String message) {
        if (StringUtils.isBlank(string)) {
            throw new PersistenceException(message);
        }
    }

    public static void mustBlank(String string, String message) {
        if (StringUtils.isNotBlank(string)) {
            throw new PersistenceException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new PersistenceException(message);
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new PersistenceException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new PersistenceException(message);
        }
    }

}
