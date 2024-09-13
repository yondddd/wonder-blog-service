package com.yond.common.utils.api;

@FunctionalInterface
public interface Factory<T> {

    /**
     * 创建对象的工厂
     *
     * @return
     */
    T newInstance();
}
