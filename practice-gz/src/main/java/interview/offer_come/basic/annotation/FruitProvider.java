package interview.offer_come.basic.annotation;

import java.lang.annotation.*;

/**
 * @author
 * @date
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) //保留策略：RUNTIME，表明注解可在运行期保留，可做反射读取（参考源码注释）
@Documented
public @interface FruitProvider {

    int id() default -1;

    String name() default "";

    String address() default "";
}
