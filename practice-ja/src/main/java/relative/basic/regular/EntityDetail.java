package relative.basic.regular;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author chenSy
 * @Date 2023/03/23 14:35
 * @Description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EntityDetail {

    String name() default "";

    String describe() default "";
}
