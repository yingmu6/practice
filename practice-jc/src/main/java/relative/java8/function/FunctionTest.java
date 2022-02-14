package relative.java8.function;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
 * 函数式接口可以被隐式转换为 lambda 表达式。
 * Lambda 表达式和方法引用（实际上也可认为是Lambda表达式）上。
 * <p>
 * https://www.runoob.com/java/java8-functional-interfaces.html
 *
 * @author chensy
 * @date 2021/9/5
 */
public class FunctionTest { //test
    public static void main(String[] args) {
        basic();
        highLeval();
    }

    // 函数式接口基本使用
    public static void basic() {
        // 使用lambda表达式来标识函数接口的实现（java8之前是使用匿名类处理的）
        GreetingService greetingService = message -> System.out.println(message + ",hello");
        greetingService.sayHello("你好");
    }

    public static void highLeval() {
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
