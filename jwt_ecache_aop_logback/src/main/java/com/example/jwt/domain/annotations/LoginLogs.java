package com.example.jwt.domain.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解类
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginLogs {
    /**
     * 描述
     */

    LoginType type();

    String value() default "";

}


