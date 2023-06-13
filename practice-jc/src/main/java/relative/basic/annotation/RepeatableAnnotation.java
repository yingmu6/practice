package relative.basic.annotation;

import java.lang.annotation.*;

/**
 * @author
 * @date
 */
@Repeatable(RepeatableAnnotationArr.class) //指定关联的注解（重复声明的注解，会按数组元素存入）
public @interface RepeatableAnnotation {
    int type() default 0;
    String name() default "";
}
