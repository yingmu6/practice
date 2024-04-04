package com.basic.use;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求上下文信息
 * @Author chenSy
 * @Date 2023/03/07 17:18
 * @Description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequestContext {
    String requestName() default "name";
    String requestId() default "id";
}
