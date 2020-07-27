package relative.basic.annotation;

import java.lang.annotation.*;

/**
 * 注解使用
 * Documented（已记录）、Retention（保留）
 */
@Documented       //元注解
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Service {
    /**
     * 性别
     */
    int sex() default 1;

    /**
     * 年龄
     */
    int age() default 1;

    /**
     * 问题集 todo @csy-new
     * 1）Documented 是否是文档标记，在什么情况下使用
     */
}
