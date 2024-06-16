package interview.written_exam.basic;

import interview.written_exam.basic.reflect_ext.A;
import interview.written_exam.basic.reflect_ext.OnlyReadClass;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class ReflectTest { //@MsY-Doing

    /**
     * 知识点：反射
     *
     * 知识点概括：
     *
     * 关联点学习：
     * 1）Class、ClassLoader源码阅读（Doing）
     *
     * 2）Member、Filed、Method源码阅读（Doing）
     *
     * 问题点答疑：
     * 1）反射的底层逻辑是什么？native内部代码是怎样的？（Doing）
     */

    /**
     * 场景1：Class对象的获取方式：
     * 1）通过className.class获取
     * 2）通过Class.forName()获取
     * 3）通过Object.getClass()获取
     */
    @Test
    public void test_get_class_by_className() { //Done
        Class<?> c = A.class;
        System.out.println("className：" + c.getName());

        /**
         * 输出结果：
         * className：com.csy.interview.no2.reflect_ext.A
         *
         * 结果分析：
         * 1）直接通过类获取对应的Class，不会执行静态块和动态构造块
         */
    }

    @Test
    public void test_get_class_by_forName() { //Done
        Class<?> c = null;
        try {
            c = Class.forName("interview.written_exam.basic.reflect_ext.A");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("className：" + c.getName());

        /**
         * 输出结果：
         * static block
         * className：com.csy.interview.no2.reflect_ext.A
         *
         * 结果分析：
         * 1）通过类名获取Class，会执行静态块（从forName源码看，底层调用forName0(...)方法时，
         *   入参initialize值为true，从字面意思来看，是会进行类初始化的，所以会调用静态块）
         */
    }

    @Test
    public void test_get_class_by_getClass() { //Done
        Class<?> c = new A().getClass();
        System.out.println("className:" + c.getName());

        /**
         * 输出结果：
         * static block
         * dynamic block
         * reflect_ext.B()
         * reflect_ext.A()
         * className:com.csy.interview.no2.reflect_ext.A
         *
         * 结果分析：
         * 1）因为需要创建对象，会进行对象的初始化，所以会执行静态块、动态构造块、成员变量的初始化
         *   （getClass()是来自于Object的方法，从方法的描述：Returns the runtime class of this {@code Object}
         *     可看出，是返回运行时的class对象，既然已经能返回完整对象了，说明已经完成对象初始化）
         */
    }

    /**
     * 场景2：通过反射，更改私有变量的值
     */
    @Test
    public void test_change_private_var() throws NoSuchFieldException, IllegalAccessException { //Done
        OnlyReadClass ro = new OnlyReadClass();
        Class clazz = OnlyReadClass.class;
        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true); //需要设置为可访问的，不然会抛出IllegalAccessException
        field.set(ro, 20);
        System.out.println(ro.getAge());

        /**
         * 输出结果：
         * 20
         *
         * 结果分析：
         * 1）通过Filed在运行时，操作字段的值
         */
    }
}
