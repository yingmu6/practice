package relative.basic.annotation;

import java.lang.annotation.*;

/**
 * @author
 * @date
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InheritedAnnotation {
    int type() default 0;
    String name() default "";
}
