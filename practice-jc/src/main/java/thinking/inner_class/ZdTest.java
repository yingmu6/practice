package thinking.inner_class;

import org.junit.Test;
import thinking.inner_class.basic.Contents;
import thinking.inner_class.basic.Destination;
import thinking.inner_class.basic.Wrapping;

/**
 * @author chensy
 * @date 2024/3/25
 */
public class ZdTest {

    /**
     * 知识点：内部类：
     *
     * 总结概括：
     * 1）内部类的作用有：
     *    a）隐藏细节：内部类可用通过private、protected等对类进行修饰，来实现信息的隐藏。
     *    b）使用外部类的所有元素：作为外部内的一个成员，可以访问外部类都所有信息（若是static内部类，就只能访问外部类static成员变量）
     *    c）可以实现多重继承：这个特点非常重要，它是内部类存在的最大理由之一。就是可以在类中声明多个内部类，然后这些内部类都继承了其它的类，然后把其他类的功能组合到当前类中了
     *    d）可以通过匿名内部类优化简单接口实现：不需要定义新的类，直接通过匿名内部类来创建对象。
     *
     * 2）外部类和内部类都会产生对应的class字节码文件，内部类的字节码文件名称："外部类名$内部类名"
     *
     * 3）内部类的创建方式：
     *    a）若是静态内部类，可通过 "new 外部类.内部类()"
     *    b）若不是静态内部类，先创建外部类的实例，然后用 "外部类实例.new 内部类()"
     *
     * 参考链接：
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

    @Test
    public void createByNew() {
        Parcel3 p = new Parcel3();
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tasmania");
        System.out.println(d.readLabel());

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）先创建外部类Parcel3的实例，再通过p.new 方式创建成员内部类的实例
         *
         * 总结概括：
         * 1）可通过外部类的实例，创建内部类实例，语法为：外部类实例引用.new 内部类构造方法
         */
    }

    /**
     * 场景4：内部类的创建方式
     */
    @Test
    public void createInnerClass() {
        Parcel4 p = new Parcel4();

        // 方式1：
        Contents c = p.contents();

        // 方式2：
//        Parcel4.PContents c1 = p.new PContents(); //此处编译报错：因为PContents是private的，不可访问

        Destination d = p.destination("Tasmania");
        System.out.println(d.readLabel());
//        Parcel4.PDestination d1 = p.new PDestination("Tasmania"); //此处报编译错误，因为PDestination的构造方法是private，不能访问的

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）通过成员方法构造成员内部类的实例并返回
         * 2）通过直接new的方式创建内部类的实例并返回
         *
         * 总结概括：
         * 1）普通的类，是不能加上private、protected等修饰的，而内部类就可以，可以通过这个特性实现内部细节隐藏
         */
    }

    /**
     * 场景5：在方法中的局部内部类
     */
    @Test
    public void partInnerClassInMethod() {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
        System.out.println(d.readLabel());

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）类Parcel5中的方法destination()中，定义了局部内部类PDestination，该类的作用范围在方法中。
         *
         * 总结概括：
         * 1）在方法中定义的类为局部内部类，可以通过这个内部类实现特定的逻辑
         */
    }

    /**
     * 场景6：在代码块中的局部内部类
     */
    @Test
    public void partInnerClassInBlock() {
        Parcel6 p = new Parcel6();
        System.out.println(p.internalTracking(true));

        /**
         * 输出结果：
         * slip
         *
         * 结果分析：
         * 1）局部内部类TrackingSlip在if语句的代码块中
         *
         * 总结概括：
         * 1）代码块中的内部类的作用范围就只是代码块中，不能超出
         */
    }

    /**
     * 场景7：匿名内部类
     */
    @Test
    public void anonymousInnerClass() {
        Parcel7 p = new Parcel7();
        Contents c = p.contents(); //此处c为匿名内部类，实例为：Parcel7$1@xxx
        System.out.println(c.value());

        /**
         * 输出结果：
         * 11
         *
         * 结果分析：
         * 1）在类Parcel7中contents()方法中，直接通过 new 接口名() {...}方式创建匿名内部类的实例
         *
         * 总结概括：
         * 1）匿名内部类是没有名称的，不用去新定义一个类
         */
    }

    /**
     * 场景7的扩展：指定名称的内部类
     */
    @Test
    public void anonymousInnerClassV2() {
        Parcel7b p = new Parcel7b();
        Contents c = p.contents();
        System.out.println(c.value()); //此处c的实例为：Parcel7b$MyContents

        /**
         * 输出结果：
         * 11
         *
         * 结果分析：
         * 1）此处Parcel7b中的MyContents，是指定名称的
         *
         * 总结概括：
         * 1）未指定名称的内部类为匿名内部类
         */
    }


    /**
     * 场景8：匿名类使用（调用基类中的方法）
     */
    @Test
    public void innerClass() {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10); //w对应的实例为：Parcel8$1@xxx
        System.out.println(w.value());

        Wrapping w2 = new Wrapping(12) { //w2对应的实例为：ZdTest$1@xxx
            @Override
            public int value() {
                return super.value() * 12; //通过super调用父类的方法
            }
        };
        System.out.println(w2.value());

        Wrapping w3 = new Wrapping(13); //w3对应的实例为：Wrapping@xxx
        System.out.println(w3.value());

        /**
         * 输出结果：
         * 470
         *
         * 结果分析：
         * 1）w、w2的类型都是匿名的内部类，可通过super访问父类Wrapping的方法
         * 2）w3是普通类Wrapping的实例
         *
         * 总结概括：
         * 1）匿名类可通过super访问父类的成员方法
         *
         */
    }

    /**
     * 场景9：匿名内部类使用
     */
    @Test
    public void innerClassV2() {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Tasmania");
        System.out.println(d.readLabel());

        /**
         * 输出结果：
         * Tasmania
         *
         * 结果分析：
         * 1）与上面用例类似
         *
         * 总结概括：
         * 1）与上面用例类似
         *
         */
    }

    /**
     * 场景10：匿名内部类
     */
    @Test
    public void innerClassV3() {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Tasmania", 101.395F);
        System.out.println(d.readLabel());

        /**
         *
         * 输出结果：
         * Over budget!
         * Tasmania
         *
         * 结果分析：
         * 1）在匿名类的代码块中，访问到外部类的方法入参值
         *
         * 总结概括：
         * 1）匿名类能访问外部类方法的入参
         *
         */
    }

    /**
     * 场景11：嵌套的内部类
     */
    @Test
    public void nestedClass() {
        Parcel11.ParcelContents parcelContents = new Parcel11.ParcelContents();;
        System.out.println(parcelContents.value());

        Parcel11.ParcelDestination parcelDestination = new Parcel11.ParcelDestination("Tasmania");
        parcelDestination.fn();

        Parcel11.ParcelDestination.AnotherLevel anotherLevel = new Parcel11.ParcelDestination.AnotherLevel(); //嵌套的静态内部类
        anotherLevel.fn();

        Parcel11.ParcelDestination.AnotherLevel.Another2 another2 = anotherLevel.new Another2("haha"); //嵌套的成员内部类
        System.out.println(another2.getStr());

        /**
         *
         * 输出结果：
         * 11
         * ParcelDestination fn()
         * AnotherLevel fn()
         * haha
         *
         * 结果分析：
         * 1）ParcelContents和ParcelDestination都是Parcel11的静态内部类，而AnotherLevel又是ParcelDestination的静态内部类，
         *   实现了内部类之间的嵌套
         *
         * 2）Another2是AnotherLevel嵌套的成员内部类，先创建AnotherLevel的实例，然后再创建Another2的实例
         *
         * 总结概括：
         * 1）可以在类中实现多层的内部类嵌套，没有层级数的限制。
         *
         */
    }


}
