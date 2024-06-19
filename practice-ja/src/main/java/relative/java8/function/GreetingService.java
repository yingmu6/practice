package relative.java8.function;

/**
 * @author chensy
 * @date 2021/9/5
 */
@FunctionalInterface
public interface GreetingService {

    // 抽象方法
    String sayHello(String message);

    //String sayHello2(String message); //此处会编译错误，因为函数式接口，只有一个抽象方法

    // 默认方法
    default String sayHi() { //可以有多个默认方法
        return " Hi ";
    }

    default String sayHi2() {
        return " Hi2 ";
    }

    // 静态方法
    static String sayHa() { //可以有多个静态方法
        return " Ha ";
    }

    static String sayHa2() {
        return " Ha2 ";
    }
}