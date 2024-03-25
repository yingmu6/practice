package com.csy.interview.offer_come.basic.inner_class;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chensy
 * @date 2024/3/20
 */
public class InnerClassTest {

    Logger logger = LoggerFactory.getLogger(InnerClassTest.class);

     /**
     * 场景1：静态内部类（static修饰）
     */
    @Test
    public void testStaticInnerClass() {
        OuterClass1.StaticInnerClass staticInnerClass = new OuterClass1.StaticInnerClass();
        staticInnerClass.getClassName();

        /**
         * 输出结果：
         * 直接访问_静态成员变量: staticInnerClass
         * 间接访问_非静态成员变量：hello
         *
         * 结果分析：
         * 1）StaticInnerClass是内部静态类，可直接通过外部类访问
         * 2）静态内部类不能直接访问非静态成员变量，会编译错误，可以通过创建外部类对象，再调用外部类中的方法实现
         *
         * 总结概括：
         * 1）静态内部类可以通过 new 外部类.静态内部类()的方法创建对象，并且可以直接访问外部类中的所有静态成员变量
         */
    }

    /**
     * 场景2：成员内部类（与成员变量相同位置）
     */
    @Test
    public void testMemberInnerClass() {

        // OuterClass2.MemberInnerClass memberInnerClass = new OuterClass2.MemberInnerClass(); //此种构建方式会编译错误，这种方法只有静态内部类方式使用
        OuterClass2 outerClass2 = new OuterClass2();
        OuterClass2.MemberInnerClass memberInnerClass = outerClass2.getMemberInnerClass();
        memberInnerClass.print();

        /**
         * 输出结果：
         * a = 1
         * b = 2
         *
         * 结果分析：
         * 1）先创建外部类实例，再通过外部类中的方法，返回内部类实例
         *
         * 总结概括：
         * 1）成员内部类，不能向静态内部类那样，直接通过外部类就可以创建实例，要通过外部类对外提供的方法，返回内部类的实例
         */
    }

    /**
     * 场景3：局部内部类（作用在方法内）
     */
    @Test
    public void testPartInnerClass() {

        OuterClass3 outerClass3 = new OuterClass3();
        outerClass3.partClassTest(3);

        /**
         * 输出结果：
         * 成员变量：a = 1，b = 2，局部变量：c = 3，d = 4，e = 5
         *
         * 结果分析：
         * 1）partClassTest()方法中定义了局部内部类PartClass，然后在该方法中创建了PartClass的实例，并对应使用其中的方法
         *
         * 总结概括：
         * 1）局部内部类的作用范围内，只能在方法内部，不能通过方法返回的形式供其它方法使用
         */
    }

    /**
     * 场景4：匿名内部类（没有名字的类）
     */
    @Test
    public void testAnonymousInnerClass() {

        OuterClass4 outerClass4 = new OuterClass4() {
            @Override
            public int workTime() {
                return 8;
            }
        };

        outerClass4.setName("张三");
//        System.out.println(OuterClass4.age); //lombok中的@Getter、@Setter不产生static变量对应的方法，所以要么自己声明，要么通过类访问
        outerClass4.printInfo();


        /**
         * 输出结果：
         * 人员信息，name：张三，age：12, workTime：8
         *
         * 结果分析：
         * 1）OuterClass4是一个abstract类，不能直接实例化，要通过它的具体实例赋值。而通过new OuterClass4(){...}的方式创建的是匿名类
         * 2）通过debug调试可知，此处的outClass4的实例是一个编译器创建的一个实例，如InnerClassTest@$1@1003
         *
         * 总结概括：
         * 1）Java中的匿名类是一种特殊的内部类，它没有名字，直接通过new关键字创建。通过创建特定接口或继承特定类的对象，覆盖某些方法，实现特定的逻辑。
         * 2）匿名内部类的内部实现原理是：编译器在编译时会构建一个继承该类或实现该该接口的子类，并将该子类的实例作为匿名类返回给程序。
         *    该子类会自动继承外部类的成员，包括静态成员和非静态成员，并且可以通过外部类的方法访问外部类的变量。
         */
    }
}
