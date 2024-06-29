package com.yond.common.utils.api;

/**
 * @version 1.0
 * @created 2018/1/7 01:25
 **/
@FunctionalInterface
public interface Factory<T> {

    /**
     * 创建对象的工厂
     *
     * @return
     */
    T newInstance();
}
