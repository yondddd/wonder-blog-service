package com.yond.extension.annotation;

import java.lang.annotation.*;

/**
 * BeanProperty
 *
 * @author muweiping
 * @version 1.0
 * @created 2020/04/05 17:03
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Spi {

    Scope scope() default Scope.SINGLETON;
}
