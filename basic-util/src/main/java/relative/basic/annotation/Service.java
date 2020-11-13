package relative.basic.annotation;

import java.lang.annotation.*;

/**
 * 注解使用
 * Documented（已记录）、Retention（保留）
 */
@Documented       //元注解
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Service {
    String name() default "test";
}
