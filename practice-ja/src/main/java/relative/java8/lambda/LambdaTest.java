package relative.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * lambda表达式测试
 *
 * @Author chenSy
 * @Date 2023/04/25 12:05
 * @Description
 */
public class LambdaTest {

    /**
     * lambda表达式详解：
     * 1）Java 8 brought a powerful new syntactic improvement in the form of lambda expressions. A lambda is an anonymous function（匿名方法）
     * that we can handle as a first-class language citizen（一等语言公民）. For instance, we can pass it to or return it from a method（可以将它传递给方法或从方法返回它）
     *
     * 2）Before Java 8, we would usually create a class for every case where we needed to encapsulate a single piece of functionality.
     * This implied a lot of unnecessary boilerplate code to define something that served as a primitive function representation.
     * （在Java 8之前，我们通常会为需要封装单个功能的每种情况创建一个类。这意味着需要大量不必要的样板代码来定义充当基本函数表示的东西。）
     *
     * 3）匿名函数（lambda函数，音标：/ˈlæmdə/ ）：lambda函数是一种快速定义单行的最小函数，是从Lisp借用来的，可以用在任何需要函数的地方
     *
     * 4）lambda允许我们将行为传到函数里。在Java 8之前，如果想将行为传入函数，仅有的选择就是匿名类。Lambda表达式取代了匿名类，取消了模板，
     *    允许用函数式风格编写代码。这样有时可读性更好，表达更清晰。
     *
     * 5）lambda表达式语法：
     *   (params) -> expression
     *   (params) -> statement
     *   (params) -> { statements; }
     *
     * 6）lambda表达式由三部分组成：参数列表、箭头、主体（lambda表示式与函数式接口的抽象方法的签名一一对应）
     *
     * 参考链接：
     * a）https://www.baeldung.com/java-8-functional-interfaces 函数式接口介绍
     * b）https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html lambda官方介绍
     * c）https://juejin.cn/post/6844903585558315016  lambda应用场景
     */

    /**
     * 场景1：lambda基本使用
     */
    @Test
    public void test_basic_use() {

        LambdaTest tester = new LambdaTest();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b; //使用lambda表达式表示函数式接口

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b; //lambda可以推断参数类型

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 调用默认方法
        System.out.println("默认方法：10 + 20 = " + addition.operationV3(10, 20));
        System.out.println("静态方法：20 + 30 = " + MathOperation.operationV4(20, 30));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("ZhangSan");
        greetService2.sayMessage("LiSi");

        /**
         * 输出结果：
         * 10 + 5 = 15
         * 10 - 5 = 5
         * 10 x 5 = 50
         * 10 / 5 = 2
         * 默认方法：10 + 20 = 30
         * 静态方法：20 + 30 = 50
         * Hello ZhangSan
         * Hello LiSi
         *
         * 结果分析：
         * 1）lambda表达式可以表示函数式接口，语法为
         *    (params) -> expression
         *    (params) -> statement
         *    (params) -> { statements; }
         * 参数可以不用声明类型，lambda会自行推断出来
         *
         * 2）函数式接口：只包含一个方法，可以使用@FunctionalInterface来修饰接口
         *             接口中可以有default、static方法
         */
    }

    final static String var = "Hello";
    /**
     * 场景2：lambda表达式中的作用域
     */
    @Test
    public void test_var_scope() {
        String localVar = "hello";
        GreetingService greetingService = message -> System.out.println(localVar + message);
        greetingService.sayMessage(" zhang");

        GreetingService greetingService2 = message -> System.out.println(var + message);
        greetingService2.sayMessage(" li");

        /**
         * 输出结果：
         * hello zhang
         * hello li
         *
         * 结果分析：（待了解）
         * 此处在lambda表达式中，能访问局部变量、成员变量，
         * 但根据https://www.runoob.com/java/java8-lambda-expressions.html 描述，只能范围final的外层局部变量，是不是java版本不同？
         * 当前运行版本是jdk17
         */
    }

    /**
     * 场景3：测试lambda表达式的应用场景
     * 1）实现Runnable接口
     * 2）集合排序Collection.sort()
     * 3）列表迭代forEach
     * 4）函数式接口Predicate
     */
    @Test
    public void test_application_scenarios_v1() {
        // Java 8之前：使用匿名类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Use anonymous class");
            }
        }).start();

        //Java 8方式：使用lambda表达式
        new Thread( () -> System.out.println("Use lambda expression") ).start();
    }

    @Test
    public void test_application_scenarios_v2() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(6);
        System.out.println("未排序前：" + list);

        list.sort((a, b) -> {
            if (a > b) { //正序还是逆序，可以通过返回的值来处理，如此处返回1，即为正序。返回-1，即为逆序
               return 1;
            } else if (a == b) {
               return 0;
            } else {
                return -1;
            }
        });
        System.out.println("已排序后：" + list);

        /**
         * 输出结果：
         * 未排序前：[5, 2, 6]
         * 已排序后：[2, 5, 6]
         *
         * 结果分析：
         * 使用lambda表达式来表示排序的比较器
         */
    }

    @Test
    public void test_application_scenarios_v3() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(3);

        // java8之前
        for (Integer var : list) {
            System.out.println(var);
        }

        System.out.println("-------分隔线--------");

        // java8之后
        list.forEach(x -> System.out.println(x));
    }

    @Test
    public void test_application_scenarios_v4() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        filter(languages, (str) -> ((String)str).startsWith("J")); //定义行为：列表中以"J"开头的字符串

        filter(languages, (str)->((String)str).endsWith("+")); ////定义行为：列表中以"+"结尾的字符串
    }

    private void filter(List<String> names, Predicate condition) {
        for(String name : names)  {
            if(condition.test(name)) { //执行谓词，若返回结果为true，则输出结果
                System.out.println("方式一：" + name + " ");
            }
        }

        // 另一种比较好的写法：
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println("方式二：" + name + " ");
        });
    }

    @FunctionalInterface
    interface MathOperation { //只有一个方法，为函数式结果，可以不带上@FunctionalInterface注解
        int operation(int a, int b); //接口中的方法都是public abstract，成员变量都是public static final

//        int operationV2(int a, int b); //函数式结果只能有一个方法，lambda表达式，可以表示函数式结果

        default int operationV3(int a, int b) { // 函数式接口中，可以包含默认方法和static方法
            return a + b;
        }

        static int operationV4(int a, int b) {
            return a + b;
        }
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

}
