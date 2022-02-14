package relative.basic.extend.order.super_test;

/**
 * @author : chensy
 * Date : 2020/11/20 上午6:55
 */
public class Test {
    public static void main(String[] args) {
        /**
         * 子类创建时，会先调用父类的无参构造方法构造父类对象，若父类提供了其它构造方法需要用super调用
         * Error:(9, 1) java: 无法将类 relative.basic.extend.order.super_test.Fruit中的构造器 Fruit应用到给定类型;
         *   需要: double
         *   找到: 没有参数
         *   原因: 实际参数列表和形式参数列表长度不同
         */
        Apple apple = new Apple();
        apple.setWeight(12.9);
        apple.setName("苹果");
        System.out.println(apple.getWeight() + ";" + apple.getName());

    }
}
