package relative.java8.function;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
 * 函数式接口可以被隐式转换为 lambda 表达式。
 * Lambda 表达式和方法引用（实际上也可认为是Lambda表达式）上。
 * <p>
 *
 * @author chensy
 * @date 2021/9/5
 */
public class FunctionTest {

    /**
     * 函数式接口详解：
     * 1）Any interface with a SAM(Single Abstract Method 单个的抽象方法) is a functional interface,
     * and its implementation may be treated as（被视为） lambda expressions
     *
     * https://www.baeldung.com/java-8-functional-interfaces demo参考
     */

    Logger logger = LoggerFactory.getLogger(FunctionTest.class);

    /**
     * 场景1：使用lambda表达式
     */
    @Test
    public void basic() {
        // 使用lambda表达式来表示函数接口的实现（java8之前是使用匿名类处理的）
        GreetingService greetingService = message -> {
            String msg = message + ",hello";
            logger.info(msg);
            return msg;
        };
        greetingService.sayHello("你好"); //抽象方法

        logger.info("default方法：{}", greetingService.sayHi()); //默认方法
        logger.info("default方法2：{}", greetingService.sayHi2());

        logger.info("static方法：{}", GreetingService.sayHa()); //静态方法（要用类名访问）
        logger.info("static方法2：{}", GreetingService.sayHa2());

        /**
         * 输出结果：
         * 你好,hello
         * default方法： Hi
         * default方法2： Hi2
         * static方法： Ha
         * static方法2： Ha2
         *
         * 结果分析：
         * 1）用lambda表示函数式接口GreetingService的实例，然后调用实例的方法时，就会进入lambda中的逻辑
         * 2）调用函数式接口中的default、static方法（static方法要用类名来访问）
         *
         * 总结概括：
         * 1）先构建函数式接口的实例，再调用实例的方法。
         * 2）函数式接口中只有一个抽象方法，可以有多个default、static方法
         */
    }

    /**
     * 场景2：内置的函数式接口使用，如Consumer、IntFunction等
     */
    @Test
    public void useEmbedded() {

        // 方式一：lambda的写法（lambda表达式对应函数式接口的抽象方法）
        Consumer<String> consumer1 = param -> logger.info(param + " V1:haha"); //先定义好函数接口
        this.dealInfo("V1：", consumer1);

        // 方式二：lambda的写法
        Consumer<String> consumer2 = (param) -> { //语句块中已有一条语句时，可以去掉{}
            logger.info(param + " V2:haha");
        };
        this.dealInfo("V2：", consumer2);

        // 方式三：匿名内部类的写法
        Consumer<String> consumer3 = new Consumer<String>() {
            @Override
            public void accept(String param) { //实现接口中逻辑（此处只是先定义函数式接口，在函数式接口使用时，才会真正调用；定义的函数式接口，使用其它的变量值）
                logger.info(param + " V3:haha");
            }
        };
        this.dealInfo("V3：", consumer3);

        String testStr = "hello";
        // 方式四：传递的函数式接口，带上传入前的变量值（当函数式接口真正使用的时候，进行回调时，会保存之前使用到的变量值，如此处的testStr）
        Consumer<String> consumer4 = param -> logger.info(param + testStr + "，V4:haha");
        this.dealInfo("V4：", consumer4);

        // IntFunction：写法一
        IntFunction intFunction1 = x -> {
            return x + 1;
        };

        IntFunction intFunction2 = x -> x / 2; //只有一条语句时，可以省去{}，lambda也能推断出返回值的

        IntFunction<String> intFunction3 = x -> "result：" + x;

        logger.info("参数值：{}，计算公式：x + 1，结果：{}", 3, intFunction1.apply(3));
        logger.info("参数值：{}，计算公式：x / 1，结果：{}", 4, intFunction2.apply(4));
        logger.info("参数值：{}，处理逻辑：result：+ x，结果：{}", 5, intFunction3.apply(5));

        /**
         * 输出结果：
         * V1： V1:haha
         * V2： V2:haha
         * V3： V3:haha
         * V4：hello，V4:haha
         * 参数值：3，计算公式：x + 1，结果：4
         * 参数值：4，计算公式：x / 1，结果：2
         * 参数值：5，处理逻辑：result：+ x，结果：result：5
         *
         * 结果分析：
         * 1）lambda表达式，其实就是一个匿名类，所以与匿名类写法最终的结果一致。
         * 2）虽然是先定义了lambda表达式，lambda的执行逻辑写在前面，但只有真实通过函数式接口的引用调用方法时，才会进入lambda定义的逻辑中
         * 3）lambda表达式要与函数式接口的抽象方法的签名保持一致
         *
         * 总结概括：
         * 1）lambda表达式对应函数式接口的抽象方法，
         *    a）如Consumer的抽象方法为void accept(T t); 该方法的含义为：接收一个参数T进行处理，不返回值，那么lambda可以表示为表示 (param) - > {...}，
         *    b）又如：IntFunction的抽象方法为R apply(int value); 该方法的含义为：接收一个int型参数，经过处理后，返回指定的R类型
         *
         */

    }

    private void dealInfo(String str, Consumer<String> consumer) { //可以通过方法参数传递
        consumer.accept(str); //执行函数接口的操作（接口的行为定义，要看原始输入的地方；该种编程风格，一开始不习惯。因为一般写代码时，都是所见即所得，逻辑都写在看到的地方，而函数式接口需要反查）
    }

    /**
     * 场景3：二元操作 BiConsumer
     * BiConsumer: Represents an operation that accepts two input arguments and returns no result（接收两个参数处理，且不返回结果）
     *
     * 参考链接：https://javabydeveloper.com/java-biconsumer-guide-examples/
     */
    @Test
    public void test_BiConsumer_V1() {
        BiConsumer<Integer, Integer> c1 = (a, b) -> logger.info("a + b = {}",a + b); //定义BiConsumer，指定具体行为
        c1.accept(10,20); //执行调用，传入实际参数

        BiConsumer<String, String> c2 = (s1, s2) -> logger.info("s1.contains(s2): {}",s1.contains(s2)); //虽然定义了BiConsumer行为，但是没有进行调用，所以此处没有输出
        BiConsumer<Integer, Integer> c3 = (a, b) -> logger.info("a * b = {}", a * b);

        // c1.andThen(c2); 语法错误，c2的类型时String，c1的类型为Integer，类型不兼容
        c1.andThen(c3).accept(5, 10); //andThen()，会返回一个组合，并按顺序执行，多个组合共用传入的参数（and then：然后）
        c1.accept(5, 10);
        c3.accept(5, 10);

        /**
         * 输出结果：
         * 30
         * 15
         * 50
         * 15
         * 50
         *
         * 结果分析：
         * 1）30：因为c1的行为a + b，即值为 10 + 20
         * 2）15：因为使用andThen，将多个BiConsumer组合，会先执行c1、在执行c3，c1值为5+10
         * 3）50：同上，因为执行c3，c3值为5*10
         * 4）15：执行c1行为，即值为5+10
         * 5）50：执行c3行为，即值为5*10
         */
    }

    @Test
    public void test_BiConsumer_V2() {
        Map<Integer, String> m = new HashMap<>();
        m.put(1, "Peter");
        m.put(2, "Mike");
        m.put(3, "John");
        m.put(4, "Mike");
        m.put(5, "Peter");
        m.put(6, "Anand");
        m.put(7, "Peter");

        BiConsumer<Integer, String> f =
                (key, value) -> logger.info("[entry="+key+", "+value+")]");

        m.forEach(f); //遍历Map中的所有条目，依次执行给定的操作

        /**
         * 输出结果：
         * [entry=1, Peter)]
         * [entry=2, Mike)]
         * [entry=3, John)]
         * [entry=4, Mike)]
         * [entry=5, Peter)]
         * [entry=6, Anand)]
         * [entry=7, Peter)]
         *
         * 结果分析：
         * Map#forEach(BiConsumer<? super K, ? super V> action)
         * 其内部实现逻辑：for循环变脸Entry，依次获取到key、value，然后依次执行BiConsumer#accept方法
         */
    }

    /**
     * 场景4：Predicates等使用
     */
    @Test
    public void highLevel() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit = new Fruit();
        fruit.setName("苹果");
        fruit.setFlavor("甜的");

        Fruit fruit2 = new Fruit();
        fruit2.setName("香蕉");
        fruit2.setFlavor("甜的");

        Fruit fruit3 = new Fruit();
        fruit3.setName("橘子");
        fruit3.setFlavor("酸的");

        fruits.add(fruit);
        fruits.add(fruit2);
        fruits.add(fruit3);

        List<Fruit> filterFruits = fruits.stream()
                .filter(x -> {
                    if (x.getFlavor().equals("甜的")) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
        logger.info(JSON.toJSONString(filterFruits));

        List<Fruit> filterFruits2 = fruits.stream()
                .filter(FunctionTest::filterFruits) //方法引用：是lambda表达式的一种特殊形式，如果正好有某个方法满足一个lambda表达式的形式，那就可以将这个lambda表达式用方法引用的方式表示
                .collect(Collectors.toList());
        logger.info("函数式接口：" + filterFruits2);
    }

    public static Boolean filterFruits(Fruit fruit) {
        // 先定义函数式接口
        CheckFunction<Fruit> function = fruit1 -> {
            if (fruit1.getFlavor().equals("酸的")) {
                return true;
            }
            return false;
        };

        // 调用函数式接口，会执行函数式接口中的具体逻辑
        return function.check(fruit);
    }
}

@Setter
@Getter
@ToString
class Fruit {
    private String name;
    private String flavor;
    private Double price;
}
