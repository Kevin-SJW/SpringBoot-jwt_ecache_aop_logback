package com.example.jwt.domain.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解类
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLogs {

    /**
     * 描述
     */
    String description() default "";
}


