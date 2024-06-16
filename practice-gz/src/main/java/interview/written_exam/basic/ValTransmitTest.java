package interview.written_exam.basic;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

/**
 * @author chensy
 * @date 2023/6/29
 */
public class ValTransmitTest { //@MsY-Doing

    /**
     * 知识点：值传递
     *
     * 知识点概括：
     * 1）When a parameter is pass-by-value, the caller and the callee method operate on two different variables which are copies of each other.
     * Any changes to one variable don't modify the other.
     * （按值传递时，调用者和被调用者操作的是两个不同的变量，仅仅是值的拷贝，任何一个变量的改变，都不会引起另一个变量的改变）
     *
     * 2）When a parameter is pass-by-reference, the caller and the callee operate on the same object.
     *  Any changes to the parameter’s instance members will result in that change being made to the original value.
     * （按引用传递时，调用者和被调用者操作的是同一个对象。任意参数实例中的成员改变，都会影响原有对象中的值）
     *
     * 注意：要区分参数是基本类型和对象类型，然后基于 基本类型是值的拷贝（两个不同的变量），对象类型时引用的拷贝（相同的对象引用），来做判断处理
     *
     * 参考链接：https://www.baeldung.com/java-pass-by-value-or-pass-by-reference
     */

    /**
     * 场景1：值传递（基本类型）
     */
    @Test
    public void test_pass_by_value() { //Done
        boolean paramVal = true;
        int val = 1;
        System.out.println("Before test(boolean) : paramVal = " + paramVal);
        test(paramVal);
        System.out.println("After test(boolean) : paramVal = " + paramVal);

        /**
         * 输出结果：
         * Before test(boolean) : paramVal = true
         * In test(boolean) : paramVal = false
         * After test(boolean) : paramVal = true
         *
         * 结果分析：
         * 1）当传递的是基本类型时，操作的是两个变量，只是值做了拷贝，所以形参的改变不影响实参
         *
         * 2）debug时，val变量显示的标签为"01"，test方法中的paramVal显示的标签为"p"
         */
    }

    public void test(boolean paramVal) {
        int val = 1;
        paramVal = !paramVal; //形参改变，不会影响实参的值（操作的是两个变量，仅仅是值的拷贝）
        System.out.println("In test(boolean) : paramVal = " + paramVal);
    }

    /**
     * 场景2：引用传递（对象类型）
     */
    @Test
    public void test_pass_by_reference() { //Done
        A aClass = new A();
        aClass.setName("zhang");
        aClass.setAge(10);
        System.out.println("Before test(A) : name = " + aClass.getName() + ", age =" + aClass.getAge());
        test(aClass);
        System.out.println("After test(A) : name = " + aClass.getName() + ", age = " + aClass.getAge());

        /**
         * 输出结果：
         * Before test(A) : name = zhang, age =10
         * In test(A) : name = zhang sir
         * After test(A) : name = zhang sir, age = 10
         *
         * 结果分析：
         * 1）当传递的是对象参数时，传递是对象的引用，操作的是同一个对象引用，所以形参的改变会影响实参
         */
    }

    public void test(A a) {
        a.setName(a.getName() + " sir"); //引用传递时，操作的是同一个对象。形参对象的成员改变，实参对象也对应改变
        System.out.println("In test(A) : name = " + a.getName());
    }

    @Getter
    @Setter
    class A {
        private String name;
        private int age;
    }

    /**
     * 场景3：String字符串传递
     */
    @Test
    public void test_pass_by_string() { //Done
        String str = "Hello";
        test(str);
        System.out.println(str);

        /**
         * 输出结果："Hello"
         *
         * 结果分析：
         * 1）String类是final类型的，不可以继承和修改这个类。所以 str = "World"，是隐含的创建一个新对象，既然产生新对象，就与原来对象没有关系
         * 2）test方法中的str，会创建一个新对象，然后指向 "World"，但test方法结束，str作用消失，不影响原有的String类
         */
    }

    public void test(String str) {
        str = "World";
    }

    /**
     * 场景4：StringBuffer字符串传递
     */
    @Test
    public void test_pass_by_string_buffer() { //Doing
        StringBuffer buffer = new StringBuffer("Hello");
        test(buffer);
        System.out.println(buffer);

        /**
         * 输出结果："Hello, World"
         *
         * 结果分析：
         * 1）StringBuffer可理解为字符串变量，传递StringBuffer时，操作的是同一个引用，所以形参的改变会影响实参
         */
    }

    public void test(StringBuffer buffer) {
        buffer.append(", World");
    }

    /**
     * 场景5：基本类型和对象类型一起使用
     */
    @Test
    public void test_pass_by_value_and_reference() {

        first();

        /**
         * 结果输出：
         * 33 0
         * 20
         *
         * 结果分析：
         * 1）因为创建了新对象，v.i指向新对象的值
         * 2）因为v.i = 20; 更改了原对象的值
         *  （这里理解上有些歧义，从直观上看由于v=val，感觉v.i也应该为33）
         *
         * 结论分析：
         * 当传递对象类型时，要区分
         * 1）引用是哪个对象的副本
         * 2）副本的值改变了，就改变相应对象的值
         */
    }

    public void first() {
        int i = 5;
        Value v = new Value();
        v.i = 25;
        second(v, i);
        System.out.println(v.i);
    }

    public void second(Value v, int i) {
        i = 0;
        v.i = 20; //通过引用的副本，改变原来对象的值为20 (此处的v是first方法中Value v对象的副本，所以会改变first方法中对象的值)

        Value val = new Value(); //创建新的对象
        v = val; //（此处v是second方法中Value val对象的副本，所以会改变当前方法中Value val的对象值）
        v.i = 33;
        System.out.println(v.i + " " + i);
    }

    class Value {
        public int i = 15;
    }
}
