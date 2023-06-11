package relative.basic.annotation;


import java.lang.reflect.Method;

/**
 * 注解使用
 * @author : chensy
 * Date : 2020/7/5 下午8:46
 */
public class AnnotationTest {

    /**
     * 注解简介 https://docs.oracle.com/javase/tutorial/java/annotations/
     *
     * Annotations, a form of metadata（元数据）, provide data about a program that is not part of the program itself.（提供的数据不是程序的一部分）
     * Annotations have no direct effect（不会直接影响） on the operation of the code they annotate.
     * Annotations have a number of uses（用途）, among them（以下是主要用途）
     *
     * 1）Information for the compiler — Annotations can be used by the compiler to detect errors or suppress warnings.
     * 2）Compile-time and deployment-time processing — Software tools can process annotation information to generate code, XML files, and so forth.
     * 3）Runtime processing — Some annotations are available to be examined at runtime.
     */
    public static void main(String[] args) { //todo 注解
        CustomerService customerService = Student.class.getAnnotation(CustomerService.class);
        System.out.println(customerService.name());

        Method[] methods = Student.class.getMethods();
        for (int i=0; i< methods.length;i++) {
            CustomerMethod methods1 = methods[i].getAnnotation(CustomerMethod.class);
            if (methods1 != null) {
                System.out.println(methods1.methodName());
            }
        }
    }

    /**
     * 场景1：使用反射获取注解中的内容 以及default的值
     */
}
