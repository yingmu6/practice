package com.csy.interview.offer_come.basic.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chensy
 * @date 2024/3/23
 */
public class ReflectTest { //todo @Ym 概要&原理

    /**
     * 反射&Class
     * 知识概要：
     *
     * 参考链接：
     */

    /**
     * 场景1：创建Class的三种方式
     */
    @Test
    public void createClass() throws ClassNotFoundException {

        // 方式一：调用对象的getClass()方法
        Person p = new Person();
        Class cls1 = p.getClass();
        System.out.println("方式一: " + cls1);

        // 方式二：通过类的class属性获取
        Class cls2 = Person.class;
        System.out.println("方式二: " + cls2);

        // 方式三：通过Class类中的forName(...)静态方法获取（最安全和性能最好的方法）
        Class cls3 = Class.forName("com.csy.interview.offer_come.basic.reflect.Person"); //此处的类名要写成完整限定名称，若写简写"Person"，就找不到类
        System.out.println("方式三: " + cls3);

        /**
         * 输出结果：
         * 方式一: class com.csy.interview.offer_come.basic.reflect.Person
         * 方式二: class com.csy.interview.offer_come.basic.reflect.Person
         * 方式三: class com.csy.interview.offer_come.basic.reflect.Person
         *
         * 结果分析：
         * 1）通过对象、类以及Class的forName获取到Class类
         *
         * 总结概括：
         * 1）通过调试，三种方式获取的Class对象实例都是同一个。
         */
    }

    /**
     * 场景2：通过Class操作类中的方法和属性
     */
    @Test
    public void operateClass() throws ClassNotFoundException {

        Class clazz = Class.forName("com.csy.interview.offer_come.basic.reflect.Person");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("反射获取的method：" + m.toString());
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("反射获取的field：" + f.toString());
        }

        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            System.out.println("反射获取的constructor：" + c.toString());
        }

        /**
         * 输出结果：
         * 反射获取的method：public void com.csy.interview.offer_come.basic.reflect.Person.setAddress(java.lang.String)
         * 反射获取的method：public java.lang.Integer com.csy.interview.offer_come.basic.reflect.Person.getAge()
         * 反射获取的method：public void com.csy.interview.offer_come.basic.reflect.Person.setAge(java.lang.Integer)
         * 反射获取的method：public java.lang.String com.csy.interview.offer_come.basic.reflect.Person.getAddress()
         * 反射获取的method：public java.lang.String com.csy.interview.offer_come.basic.reflect.Person.getName()
         * 反射获取的method：public void com.csy.interview.offer_come.basic.reflect.Person.setName(java.lang.String)
         * 反射获取的field：private java.lang.String com.csy.interview.offer_come.basic.reflect.Person.name
         * 反射获取的field：private java.lang.Integer com.csy.interview.offer_come.basic.reflect.Person.age
         * 反射获取的field：private java.lang.String com.csy.interview.offer_come.basic.reflect.Person.address
         * 反射获取的constructor：public com.csy.interview.offer_come.basic.reflect.Person()
         * 反射获取的constructor：public com.csy.interview.offer_come.basic.reflect.Person(java.lang.String,java.lang.Integer,java.lang.String)
         *
         * 结果分析：
         * 1）获取到Class的对象实例后，获取到相应的方法Method、字段Field信息
         *
         * 总结概括：
         * 1）获取到Class对象，就能拿到运行时类的信息
         *
         */
    }

    /**
     * 场景3：通过反射创建对象的两种方式
     */
    @Test
    public void createObject() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // 方式一：
        Class clazz = Class.forName("com.csy.interview.offer_come.basic.reflect.Person");
        Person person1 = (Person) clazz.newInstance();
        System.out.println("通过Class的newInstance创建对象，" + person1);

        // 方式二：
        Constructor constructor = clazz.getDeclaredConstructor(String.class, Integer.class, String.class);
        Person person2 = (Person) constructor.newInstance("张三", 30, "小河村");
        System.out.println("通过Constructor的newInstance创建对象，" + person2);

        /**
         * 输出结果：
         * 通过Class的newInstance创建对象，com.csy.interview.offer_come.basic.reflect.Person@b065c63
         * 通过Constructor的newInstance创建对象，com.csy.interview.offer_come.basic.reflect.Person@7bfcd12c
         *
         * 结果分析：
         * 1）通过Class对象的newInstance()方法创建对象
         * 2）沟通Constructor的newInstance()方法创建对象
         *
         * 总结概括：
         * 1）在代码中可以通过new构建对象，也可以通过反射机制在运行时创建对象
         *
         */
    }

    /**
     * 场景4：Method的invoke方法使用
     */
    @Test
    public void useInvoke() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class clazz = Class.forName("com.csy.interview.offer_come.basic.reflect.Person");
        Method method = clazz.getMethod("setName", String.class);
        Constructor constructor = clazz.getConstructor();

        Object object = constructor.newInstance();
        method.invoke(object, "alex");

        Method method1 = clazz.getMethod("getName");
        System.out.println("getName()返回值：{}" + method1.invoke(object));

        /**
         * 输出结果：
         * getName()返回值：alex
         *
         * 结果分析：
         * 1）获取到Method对象后，就可以通过invoke调用
         *
         * 总结概括：
         * 1）Method包含方法的所有信息
         */
    }
}
