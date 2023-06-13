package relative.basic.annotation;

import java.lang.annotation.*;

/**
 * 注解使用
 * Documented（已记录）、Retention（保留）
 */
@Documented       //元注解
@Retention(RetentionPolicy.RUNTIME)
//@Retention(RetentionPolicy.SOURCE) //若用反射机制获取注解会报出NPE异常，因为SOURCE是保留在源码中的，在运行时已经不存在了
@Target({ElementType.TYPE})
public @interface GeneralAnnotation {

    Class<?> interfaceClass() default void.class; //对应的接口Class

    String name() default "test";

    int age() default 10;

    double weight();
}
