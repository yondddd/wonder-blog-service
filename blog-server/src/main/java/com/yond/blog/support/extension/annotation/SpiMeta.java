package com.yond.blog.support.extension.annotation;

import java.lang.annotation.*;

/**
 * SpiMeta
 *
 * @author
 * @version 1.0
 * @created 2020/04/05 17:03
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SpiMeta {

    String name() default "";
}
