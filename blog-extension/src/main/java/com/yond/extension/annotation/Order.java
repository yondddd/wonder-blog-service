package com.yond.extension.annotation;

import java.lang.annotation.*;

/**
 * Order
 *
 * @version 1.0
 * @created 2021/05/11 02:21
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface Order {

    int value() default Integer.MIN_VALUE;
}