package relative.java8.function;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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


    /**
     * 场景1：使用lambda表达式
     */
    @Test
    public void basic() {
        // 使用lambda表达式来标识函数接口的实现（java8之前是使用匿名类处理的）
        GreetingService greetingService = message -> System.out.println(message + ",hello");
        greetingService.sayHello("你好");
    }

    /**
     * 场景2：内置的函数式接口使用，如Consumer、IntFunction等
     */
    @Test
    public void useEmbedded() {
        // 方式一：lambda的写法
        Consumer<String> consumer1 = param -> System.out.println(param + " V1:haha"); //先定义好函数接口
        this.dealInfo("function interface V1：", consumer1);

        // 方式二：lambda的写法
        Consumer<String> consumer2 = (param) -> { //语句块中已有一条语句时，可以去掉{}
            System.out.println(param + " V2:haha");
        };
        this.dealInfo("function interface V2：", consumer2);

        // 方式三：匿名内部类的写法
        Consumer<String> consumer3 = new Consumer<String>() {
            @Override
            public void accept(String param) { //实现接口中逻辑（此处只是先定义函数式接口，在函数式接口使用时，才会真正调用；定义的函数式接口，使用其它的变量值）
                System.out.println(param + " V3:haha");
            }
        };
        this.dealInfo("function interface V3：", consumer3);

        String testStr = "hello";
        // 方式四：传递的函数式接口，带上传入前的变量值（当函数式接口真正使用的时候，进行回调时，会保存之前使用到的变量值，如此处的testStr）
        Consumer<String> consumer4 = param -> System.out.println(param + testStr + " V4:haha");
        this.dealInfo("function interface V4：", consumer4);

        // IntFunction：写法一
        IntFunction intFunction1 = x -> {
            return x + 1;
        };

        IntFunction intFunction2 = x -> x / 2; //只有一条语句时，可以省去{}，lambda也能推断出返回值的

        System.out.println("int计算1：" + intFunction1.apply(3));
        System.out.println("int计算2：" + intFunction2.apply(4));
    }

    private void dealInfo(String str, Consumer<String> consumer) { //可以通过方法参数传递
        consumer.accept(str); //执行函数接口的操作（接口的行为定义，要看原始输入的地方；该种编程风格，一开始不习惯。因为一般写代码时，都是所见即所得，逻辑都写在看到的地方，而函数式接口需要反查）
    }

    /**
     * 场景3：二元操作 BiConsumer、BiFunction
     */
    public void testBinaryOperator() {

    }

    /**
     * 场景4：Predicates等使用
     */
    @Test
    public void highLeval() {
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
        System.out.println(filterFruits);

        List<Fruit> filterFruits2 = fruits.stream()
                .filter(FunctionTest::filterFruits) //方法引用：是lambda表达式的一种特殊形式，如果正好有某个方法满足一个lambda表达式的形式，那就可以将这个lambda表达式用方法引用的方式表示
                .collect(Collectors.toList());
        System.out.println("函数式接口：" + filterFruits2);
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
