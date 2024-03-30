package thinking.inner_class.case5;

/**
 * @author chensy
 * @date 2024/3/26
 */
public class MyIncrement {

    public void increment() {
        System.out.println("Other operation");
    }

    static void f(MyIncrement mi) {
        mi.increment(); //可访问创建对象的方法（说明传入的实例，在当前类初始化前就创建好的）
        // increment(); //此处会编译错误，静态方法不能调用非静态方法
    }
}
