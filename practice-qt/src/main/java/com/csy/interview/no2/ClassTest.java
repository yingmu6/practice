package com.csy.interview.no2;

import com.csy.interview.no2.reflect_ext.A;
import com.csy.interview.no2.reflect_ext.OnlyReadClass;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author chensy
 * @date 2023/7/24
 */
public class ClassTest {

    /**
     * 反射_测试
     */

    /**
     * 场景1：Class对象的获取方式：
     * 1）通过className.class获取
     * 2）通过Class.forName()获取
     * 3）通过Object.getClass()获取
     */
    @Test
    public void test_get_class_by_className() {
        Class<?> c = A.class;
        System.out.println("className:" + c.getName());

        /**
         * 输出结果：
         * className:com.csy.interview.no2.reflect_ext.A
         *
         * 结果分析：
         * 直接通过类获取对应的Class，不会执行静态块和动态构造块
         */
    }

    @Test
    public void test_get_class_by_forName() {
        Class<?> c = null;
        try {
            c = Class.forName("com.csy.interview.no2.reflect_ext.A");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("className:" + c.getName());

        /**
         * 输出结果：
         * static block
         * className:com.csy.interview.no2.reflect_ext.A
         *
         * 结果分析：
         * 通过类名获取Class，会执行静态块
         */
    }

    @Test
    public void test_get_class_by_getClass() {
        Class<?> c = new A().getClass();
        System.out.println("className:" + c.getName());

        /**
         * 输出结果：
         * static block
         * dynamic block
         * className:com.csy.interview.no2.reflect_ext.A
         *
         * 结果分析：
         * 因为需要需要创建对象，会进行初始化，所以会执行静态块和动态构造块
         */
    }

    /**
     * 场景2：通过反射，更改私有变量的值
     */
    @Test
    public void test_change_private_var() throws NoSuchFieldException, IllegalAccessException {
        OnlyReadClass ro = new OnlyReadClass();
        Class clazz = OnlyReadClass.class;
        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true); //需要设置为可访问的，不然会抛出IllegalAccessException
        field.set(ro, 20);
        System.out.println(ro.getAge());
    }
}
