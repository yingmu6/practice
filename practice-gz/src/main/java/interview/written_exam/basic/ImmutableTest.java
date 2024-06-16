package interview.written_exam.basic;

import org.junit.Test;

import java.util.Date;

/**
 * @author chensy
 * @date 2023/8/10
 */
public class ImmutableTest { //@MsY-Done
    /**
     * 知识点：不可变类
     *
     * 知识点概要：
     * 1）不可变类：是指创建了这个类的实例后，在其整个生命周期中，它的成员变量就不能被修改
     * 2）创建不可变类的四条基本原则：
     *    a）类中的所有成员变量被private修饰
     *    b）类中没有写或者修改成员变量的方法
     *    c）确保类中的所有方法不被子类覆盖，可以通过把类定义为final或把方法定义为final来实现
     *    d）如果一个类成员变量不是不可变量，需要在成员初始化或get方法获取该成员变量时，使用clone方法来确保类的不可变性
     *
     * 3）不可变类有许多优点，使用简单、线程安全，节省内存等，比如String类、基本类型的封装类等等
     *    但也有弊端，不可变对象会因为值的不同产生新的对象，从而导致出现无法预料的问题，所以，不可滥用这种模式
     */

    /**
     * 场景1：不可变类（设计有缺陷的场景）
     */
    @Test
    public void test_immutable_class_v1() { //Done_传参为引用类型时，未做好限制，导致可变
        Date d = new Date();
        ImmutableClassV1 cls = new ImmutableClassV1(d);
        cls.printState();
        d.setDate(5);
        cls.printState();

        /**
         * 输出结果：
         * Sun May 19 19:56:10 CST 2024
         * Sun May 05 19:56:10 CST 2024
         *
         * 结果分析：
         * 从结果上来看，ImmutableClass成员变量Date d的发生了改变
         * 原因是：Date d没有定义为不可变量，在构造时传递的是引用，引用改变，对应的成员变量值也改变
         */
    }

    class ImmutableClassV1 {
        private Date d;
        public ImmutableClassV1(Date d) { //参数为引用参数
            this.d = d;
        }
        public void printState() {
            System.out.println(d);
        }
    }

    /**
     * 场景2：不可变类（设计良好的场景）
     */
    @Test
    public void test_immutable_class_v2() { //Done_使用clone方式解决参数为引用的问题
        Date d = new Date();
        ImmutableClassV2 cls = new ImmutableClassV2(d);
        cls.printState();
        d.setDate(5);
        cls.printState();

        /**
         * 输出结果：
         * Sun May 19 19:59:34 CST 2024
         * Sun May 19 19:59:34 CST 2024
         *
         * 结果分析：
         * 从结果上来看，ImmutableClass成员变量Date d使用了clone方式确保不可变
         * 因为做了clone拷贝后，不是同一个引用，两个引用之间的内容不会相互影响
         */
    }

    class ImmutableClassV2 {
        private Date d;
        public ImmutableClassV2(Date d) {
            this.d = (Date) d.clone(); //clone后产生新的引用，互不影响
        }
        public void printState() {
            System.out.println(d);
        }
    }
}
