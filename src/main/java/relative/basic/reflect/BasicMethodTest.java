package relative.basic.reflect;

/**
 * 基本方式使用
 * @author : chensy
 * Date : 2020-03-05 08:13
 */
public class BasicMethodTest {
    public static void main(String[] args) {
        //isPrimitive();
        isAssignableFrom();
    }

    // 判断是否是基本类型
    private static void isPrimitive() {
        Boolean a = Boolean.TRUE;
        boolean b = true;
        System.out.println("是否是基本类型：" + a.getClass().isPrimitive() + ",");

        Class booleanClass = Boolean.class; //封装类型，不是基本类型
        System.out.println("Boolean is primitive type："+ booleanClass.isPrimitive());

        Class booleanType = boolean.class; //是基本类型
        System.out.println("boolean is primitive type："+ booleanType.isPrimitive());
    }


    private static void isAssignableFrom() {
        Apple apple = new Apple();
        System.out.println(apple.getClass().isAssignableFrom(Fruits.class));
        System.out.println(Fruits.class.isAssignableFrom(apple.getClass()));

        System.out.println(Apple.class.isAssignableFrom(apple.getClass()));

        RedApple redApple = new RedApple();
        System.out.println(redApple.getClass().isAssignableFrom(Apple.class));
        System.out.println(Apple.class.isAssignableFrom(redApple.getClass()));

        /**
         * 输出结果：
         * false
         * true
         * true
         * false
         * true
         */

    }
}
