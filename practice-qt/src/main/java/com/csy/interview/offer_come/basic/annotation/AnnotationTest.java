package com.csy.interview.offer_come.basic.annotation;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class AnnotationTest { //@MsY-Done

    /**
     * 知识点：注解基本使用
     *
     * 知识点概括：
     * 1）Java注解是附加在代码中的一些元信息，用于工具在编译、运行时的解析和使用，起到说明、配置的功能。并不影响代码的实例逻辑，仅仅起到辅助作用。
     * 2）注解本质是一个继承Annotation的接口（不需要显示声明），其具体实现类是Java运行时生成的代理类。在反射获取注解时，返回的是动态代理对象$Proxy。
     *    通过代理对象调用注解方法时，最终会调用AnnotationInvocationHandler的invoke方法。（可通过debug以及javap看到）
     *
     * 参考链接：https://blog.unclezs.com/pages/83a49c/#%E6%B3%A8%E8%A7%A3%E7%9A%84%E7%94%A8%E5%A4%84
     */

    /**
     * 通过javap查看编译后注解的特性
     * 执行： javap /Users/shengyong.chen/self_remote/practice/practice-qt/target/classes/com/csy/interview/offer_come/basic/annotation/FruitProvider.class
     *
     * 得到注解编译后的字节码文件（可以看到注解是一个接口，且继承了Annotation接口）
     *
     * public interface com.csy.interview.offer_come.basic.annotation.FruitProvider extends java.lang.annotation.Annotation {
     *   public abstract int id();
     *   public abstract java.lang.String name();
     *   public abstract java.lang.String address();
     * }
     */
    @Test
    public void basicUse() {

        FruitInfoUtil.getFruitInfo(Apple.class);

        /**
         * 输出结果：
         * 供应商编号：1，供应商名称：红富士，供应商地址：陕西
         *
         * 结果分析：
         * 1）定义的注解@FruitProvider的保留策略为RUNTIME，即@Retention(RetentionPolicy.RUNTIME)，表明该注解在运行时期有效。
         * 2）可通过反射机制获取到注解实例，如：field.isAnnotationPresent(FruitProvider.class) 和 field.getAnnotation(FruitProvider.class);
         * 3）获取到注解实例后，即可调用注解中的方法，获取到注解上声明的值，如fruitProvider.id()
         *
         */
    }
}
