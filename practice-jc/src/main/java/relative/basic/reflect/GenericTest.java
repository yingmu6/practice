package relative.basic.reflect;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 泛化类型测试
 *
 * @Author chenSy
 * @Date 2023/04/26 11:01
 * @Description
 */
public class GenericTest {

    /**
     * 泛化参数类型_概述
     * 1）JDK 5.0 introduced Java Generics with the aim of（目的） reducing bugs and adding an extra layer of abstraction over types（额外的类型抽象层）.
     * （泛型是jdk5.0引入的功能，目标是减少bug，并在类型之上增加一个额外的抽象层）
     *
     * 2）Type Erasure 类型擦除
     * Generics were added to Java to ensure type safety.  And to ensure that generics won't cause overhead at runtime,
     * the compiler applies a process called type erasure on generics at compile time.
     * （泛型被添加到Java中以确保类型安全。为了确保泛型不会在运行时造成开销，编译器在编译时对泛型应用一个称为类型擦除的过程。）
     *
     * Type erasure removes all type parameters and replaces them with their bounds or with Object if the type parameter is unbounded.
     * This way, the bytecode after compilation contains only normal classes, interfaces and methods,
     * ensuring that no new types are produced.  Proper casting is applied as well to the Object type at compile time
     * （类型擦除删除所有类型参数，并用它们的边界替换它们，如果类型参数没有边界，则用Object替换它们。
     * 这样，编译后的字节码只包含普通的类、接口和方法，确保不会产生新的类型。在编译时也对Object类型应用适当的类型转换）
     *
     * 3）Java Generics is a powerful addition to the Java language because it makes the programmer's job easier and less error-prone.
     * Generics enforce type correctness at compile time and,most importantly, enable implementing generic algorithms
     * without causing any extra overhead to our applications
     * （java泛化类型是比较强大的功能，它可以减少程序人员减少错误，因为在编译期间就会强制检查类型
     * 【若不设置类型，在取值的时候，就会强转类型，强转的类型时就很容易出错，也就是编译器不报错，而运行期报错，这是很头疼的事情了！！！】）
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-generics
     * b）https://docs.oracle.com/javase/tutorial/java/generics/types.html java官方文档
     */

    /**
     * 场景1：泛型基本使用
     */
    @Test
    public void basicUse() {
        String[] strArr = new String[]{"aaa", "bbb", "ccc"};
        Integer[] intArr = new Integer[]{111, 222, 333};
        System.out.println("字符串类型的数组转换：" + fromArrayToList(strArr));
        System.out.println("整型类型的数组转换：" + fromArrayToList(intArr)); //在调用方法时，要指明泛型的具体类型
    }

    private <T> List<T> fromArrayToList(T[] a) { //方法上/参数中使用泛型参数：在方法的返回类型前声明类型
        return Arrays.stream(a).collect(Collectors.toList());
    }

     /**
     * 场景2：上界、下界，多泛型使用
     */
     @Test
     public void useWithBound(){
         String[] strArr = new String[]{"aaa", "bbb", "ccc"};
         Integer[] intArr = new Integer[]{111, 222, 333};

         /**
          * 设置了泛型以后，此处直接在编译时就报错，若没设置泛型控制，则可能在运行期，类型强转的时候报错（运行时报错，可能影响业务了，所以还是提前检查出异常）
          * 会抛出编译时异常信息：“reason: inferred type does not conform（符合、遵守） to upper bound(s)”，即引用的类型不满足上界类型要求
          */
//         System.out.println("字符串类型的数组转换：" + fromArrayToListV2(strArr));
         System.out.println("整型类型的数组转换：" + fromArrayToListV2(intArr));
     }

     private <T extends Number> List<T> fromArrayToListV2(T[] a) { //设置了泛型的上界，即使方法名称与上面相同，方法签名也是不一样了，就不会报错。
         return Arrays.stream(a).collect(Collectors.toList());
     }



     /**
     * 场景3：ParameterizedType、Type等类型使用
     */

    /**
     * 场景4：了解泛型擦除后的字节码文件
     */

}
