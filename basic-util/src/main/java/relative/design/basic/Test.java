package relative.design.basic;

/**
 * @author : chensy
 * Date : 2020-03-01 14:54
 */
public class Test {
    public static void main(String[] args) {
        // 普通接口实现
        Target target = new CommonTarget();
        target.sayHello();

        // 类适配器实现
        Target target1 = new AdapterForClass("en");
        target1.sayHello();

        // 对象适配器实现
        Target target2 = new AdapterForObject(new Adaptee(), "zh");
        target2.sayHello();

    }
}
