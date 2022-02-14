package relative.basic.annotation;


import java.lang.reflect.Method;

/**
 * 注解使用
 * @author : chensy
 * Date : 2020/7/5 下午8:46
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Service service = Student.class.getAnnotation(Service.class);
        System.out.println(service.name());

        Method[] methods = Student.class.getMethods();
        for (int i=0; i< methods.length;i++) {
            relative.basic.annotation.Method methods1 = methods[i].getAnnotation(relative.basic.annotation.Method.class);
            if (methods1 != null) {
                System.out.println(methods1.methodName());
            }
        }
    }

    /**
     *
     */
}
