package relative.basic.annotation;


import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 注解使用
 * @author : chensy
 * Date : 2020/7/5 下午8:46
 */
public class AnnotationTest {

    /**
     * 注解_概述：
     * （Java注解（Annotation）又称Java标注，是JDK5.0引入的一种注释机制，按字面意思理解，它就是用来注释用的，和商场里的标签一样）
     *
     * 1）Annotations, a form of metadata（元数据）, provide data about a program that is not part of the program itself.
     * （注解是元数据的一种形式，提供的数据不是程序的一部分）
     *
     * 2）Annotations have no direct effect（不会直接影响） on the operation of the code they annotate.
     * （注解不会直接影响它注解的代码的操作）
     *
     * 3）Annotations have a number of uses（用途）, among them（以下是主要用途）
     *    a）Information for the compiler — Annotations can be used by the compiler to detect errors or suppress warnings.
     *       (用于编译器信息：注解可用于编译器检查错误或提醒)
     *    b）Compile-time and deployment-time processing — Software tools can process annotation information to generate code, XML files, and so forth（等等）.
     *      （用于编译时和部署时处理：软件工具能处理注解信息来产生代码、XML文件等等）
     *    c）Runtime processing — Some annotations are available to be examined（检查） at runtime.
     *      （运行时处理：一些注解可用于运行时检查）
     *
     * 4）Annotation types are a form of interface，the annotation type definition looks similar to an interface
     * （注解是接口的一种形式，看起来像接口）---从定义上看，比接口多了@interface以及方法上多了default
     *
     * 5）预定义的注解：@Deprecated, @Override, and @SuppressWarnings.
     *
     * 6）元注解：Annotations that apply to other annotations are called meta-annotations
     *    a）@Retention annotation specifies how the marked annotation is stored（Retention：保留策略，也可以指注解类的生命周期）
     *     （简而言之便是SOURCE代表只在源码中有效，CLASS代表在编译器有效，RUNTIME代表会在运行期有效。从SOURCE到RUNTIME他们的效用是递增的）
     *
     *    b) @Documented annotation indicates that whenever the specified annotation is used those elements should be documented using the Javadoc tool.
     *     （Documented的作用只有一点：在使用javadoc生成api文档时会将使用Documented注解类作用的类上加上注解类的信息）
     *
     *    c）@Target annotation marks another annotation to restrict what kind of Java elements the annotation can be applied to.
     *     （@Target注解指定作用的java元素类型，即注解的作用目标）
     *
     *    d) @Inherited annotation indicates that the annotation type can be inherited from the super class.
     *     （@Inherited用于继承其它的注解）
     *
     * 参考链接：
     * a）https://docs.oracle.com/javase/tutorial/java/annotations Oracle官网说明
     * b）https://www.liaoxuefeng.com/wiki/1252599548343744/1265102803921888 注解使用
     */
    @Test
    public void test_basic() {
        GeneralAnnotation generalAnnotation = Student.class.getAnnotation(GeneralAnnotation.class);
        System.out.println(generalAnnotation.name()); //输出："zhangSan"

        Method[] methods = Student.class.getMethods(); //注意：getMethods()返回的是所有public方法，private就不包含了
        for (int i=0; i< methods.length;i++) {
            MethodAnnotation methods1 = methods[i].getAnnotation(MethodAnnotation.class);
            if (methods1 != null) {
                System.out.println(methods1.methodName()); //输出："getSex"
            }
        }
    }

    /**
     * 场景1：使用反射获取注解中的内容 以及default的值
     * a）通过反射获取注解实例 Xxx.class.getAnnotation(Xxx.class)
     * b）调用注解中的方法，获取注解上设置的值，如customerService.name()
     * c）Method中的getDefaultValue()，表示返回注解成员的默认值。
     */
    @Test
    public void test_extract_value() throws InvocationTargetException, IllegalAccessException {
        GeneralAnnotation generalAnnotation = Student.class.getAnnotation(GeneralAnnotation.class); //获取类上声明的注解
        System.out.println("注解中的值：name=" + generalAnnotation.name() + ",age=" + generalAnnotation.age() + ",weight=" + generalAnnotation.weight());

        Method[] methods = generalAnnotation.getClass().getMethods();
        for (int i=0; i<methods.length; i++) {
            if (methods[i].getName().equals("name")
                    || methods[i].getName().equals("age")
                    || methods[i].getName().equals("weight")) {
                Object value = methods[i].invoke(generalAnnotation);
                System.out.println("使用反射获取注解中属性的值：" + value + "，注解中声明的默认值：" + methods[i].getDefaultValue()); //todo @csy 此处getDefaultValue()返回注解方法上的默认值时，为啥都为null
            }
        }
    }

    /**
     * 场景2：注解的继承
     */
    @Test
    public void test_inherited() {
        /**
         * 说明：Man继承了Person类，而Person上声明了@InheritedAnnotation注解，该注解带有@Inherited，是允许继承的。所以Man上就继承了Person上的注解
         */
        InheritedAnnotation inherited = Man.class.getAnnotation(InheritedAnnotation.class);
        System.out.println("获取类继承来的注解值：" + inherited.type() + "，" + inherited.name()); //输出："获取类继承来的注解值：1，人类"
    }

    /**
     * 场景3：可重复的注解
     */
    @Test
    public void test_repeatable() {
//        RepeatableAnnotation repeatableAnnotation = Man2.class.getAnnotation(RepeatableAnnotation.class);
//        System.out.println(repeatableAnnotation.type() + "," + repeatableAnnotation.name()); //直接获取会报NPE

        RepeatableAnnotationArr repeatableAnnotationArr = Man2.class.getAnnotation(RepeatableAnnotationArr.class); //需要先获取注解的数组，然后再获取具体的注解
        RepeatableAnnotation repeatableAnnotation = repeatableAnnotationArr.value()[0];
        System.out.println("第一个注解：" + repeatableAnnotation.type() + "," + repeatableAnnotation.name());

        RepeatableAnnotation repeatableAnnotation2 = repeatableAnnotationArr.value()[1];
        System.out.println("第二个注解：" + repeatableAnnotation2.type() + "," + repeatableAnnotation2.name());
    }

    /**
     * 待解决的问题点：
     * 1）使用Method的getDefaultValue() 获取注解中默认值时为null
     */
}
