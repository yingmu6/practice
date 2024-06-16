package interview.written_exam.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2024/5/22
 */
public class OverrideTest { //@MsY-Done

    /**
     * 知识点：Override重写
     *
     * 知识点概要：
     * 1）要区分出重载、重写方法，不要进行混淆
     *
     * 2）若是重写方法，要尽量带上@Override注解，及时发现问题
     *
     * 3）重写和重载的区分：
     *    3.1）重写（Override）是指子类定义了一个与其父类中具有相同名称、参数列表和返回类型的方法，
     *         并且子类方法的实现覆盖了父类方法的实现。 即外壳不变，核心重写！
     *    3.2）重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。
     *
     * 参考链接：
     * https://www.runoob.com/java/java-override-overload.html
     */
    @Test
    public void basicUse() { //Done
        List list = new ArrayList<>();
        ATest test1 = new ATest("haha");
        ATest test2 = new ATest("haha");
        Object test3 = new ATest("haha");
        Object test4 = new ATest("haha");

        list.add(test1);
        list.add(test2);
        list.add(test3);

        System.out.println(list.contains(test1));
        System.out.println(list.contains(test2));
        System.out.println(list.contains(test3));

        System.out.println(test1.equals(test2));
        System.out.println(test2.equals(test3));
        System.out.println(test3.equals(test2));
        System.out.println(test3.equals(test4));

        /**
         * 输出结果：
         * true
         * true
         * true
         * true
         * true
         * true
         * true
         *
         * 结果分析：
         * 1）ArrayList的constans()方法中，会调用元素中的equals方法，如果重写了equals方法
         *   就会调用重写的equals方法。因为Object是所有对象的父类，将具体对象赋值给Object，
         *   相当于向上转型，也就是实现了多态，父类就会调用子类的重写的方法
         *
         * 2）特别注意：要区分重载和重写，最好加上@Override，如果重写有误，编译会及时报出来
         *    不然有时候觉得是重写，但是由于参数写的不对，可能方法实际是重载
         *
         * 3）如果具体实例没有重写Object的equals方法，那就会调用Object的equals方法
         */
    }

    static class ATest {

        private String content;

        public ATest(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) { //比较对象地址
                return true;
            }

            if (o instanceof ATest) {
                ATest test = (ATest) o;
                return test.getContent().equals(content); //比较对象内容
            }
            return false;
        }
    }

}
