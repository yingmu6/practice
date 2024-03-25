package thinking.inner_class;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/25
 */
public class ZdInnerClassTest {

    /**
     * 内部类的测试：
     *
     * 内部类的概要：
     * 1）内部类的作用有：
     *    a）隐藏细节：内部类可用通过private、protected等对类进行修饰，来实现信息的隐藏。
     *    b）使用外部类的所有元素：作为外部内的一个成员，可以访问外部类都所有信息（若是static内部类，就只能访问外部类static成员变量）
     *    c）可以实现多重继承：这个特点非常重要，它是内部类存在的最大理由之一。就是可以在类中声明多个内部类，然后这些内部类都继承了其它的类，然后把其他类的功能组合到当前类中了
     *    d）可以通过匿名内部类优化简单接口实现：不需要定义新的类，直接通过匿名内部类来创建对象。
     *
     * 相关参考链接：
     * 1）https://worktile.com/kb/p/37738#
     */

    /**
     * 场景1：测试成员内部类的基本使用
     */
    @Test
    public void basicUse() {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）先创建外部类Parcel1的对象，然后调用成员方法ship()，该方法中包含了对成员内部类的创建及访问
         *
         * 总结概括：
         * 1）成员内部类的作用位置和成员变量、成员方法相同，可当做成员变量来使用
         */
    }

    /**
     * 场景2：成员内部类实例的创建（通过成员方法）
     */
    @Test
    public void createByMemberMethod() {
        Parcel2 p = new Parcel2();
        p.ship("Tasmania");
        Parcel2 q = new Parcel2();
        Parcel2.Contents c = q.contents();
        Parcel2.Destination d = q.to("Borneo");

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）通过成员方法contents()创建成员内部类
         *
         * 总结概括：
         * 1）成员变量的引用声明语法为：外部类.内部类，如Parcel2.Contents
         */
    }

    /**
     * 场景3：成员内部类实例的创建（通过外部对象实例new）
     */
    public void test() {
        Parcel3 p = new Parcel3();
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tasmania");

        /**
         * 输出结果：
         * （输出空白内容）
         *
         * 结果分析：
         * 1）先创建外部类Parcel3的实例，再通过p.new 方式创建成员内部类的实例
         *
         * 总结概括：
         * 1）可通过外部类的实例，创建内部类实例，语法为：外部类实例引用.new 内部类构造方法
         */
    }
}
