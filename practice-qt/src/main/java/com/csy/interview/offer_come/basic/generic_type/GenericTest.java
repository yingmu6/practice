package com.csy.interview.offer_come.basic.generic_type;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensy
 * @date 2024/3/18
 */
public class GenericTest {

    Logger logger = LoggerFactory.getLogger(GenericTest.class);

     /**
     * 场景1：泛型类使用
     */
    @Test
    public void testGenericClass() {
        GeneralClass<Integer> genInt = new GeneralClass<>();
        genInt.add(1);
        logger.info("{}", genInt.get()); //使用占位符格式化输出（因为logger输出的是String类型，其它类型的需要格式化一下）

        GeneralClass<String> genStr = new GeneralClass<>();
        genStr.add("2");
        logger.info(genStr.get());

        /**
         * 输出结果：
         * 1
         * 2
         *
         * 结果分析：
         * 1）GeneralClass<T>，类上声明了泛型，为泛型类。类中可以基于这个泛型类型T处理。
         * 2）因为该类型T没有具体定义上界和下界，所以相当于Object，可以接收任何类型的数据。
         *
         * 总结概括：
         * 1）类中使用到的泛型，在类的定义上做声明，如GeneralClass
         */
    }

     /**
     * 场景2：泛型接口使用
     */
    @Test
    public void testGenericInterface() {
        GeneralIntegerImpl genInt = new GeneralIntegerImpl();
        genInt.setId(10);
        logger.info("{}", genInt.getId());

        GeneralDoubleImpl genDouble = new GeneralDoubleImpl();
        genDouble.setId(10.05);
        logger.info("{}", genDouble.getId());

        List<IGeneral> list = new ArrayList<>();
        list.add(genInt);
        list.add(genDouble);
        logger.info(JSON.toJSONString(list));

        /**
         * 输出结果：
         * 10
         * 10.05
         * [{"id":10},{"id":10.05}]
         *
         * 结果分析：
         * 1）GeneralInterImpl、GeneralDoubleImpl在实现泛型接口时，指明了具体的类型，维护不同类型的成员变量
         * 2）List<IGeneral>类型，可添加IGeneral的实例
         *
         * 总结概括：
         * 1）泛型编程是一种编程技术，允许程序员编写处理各种不同类型的通用算法和数据接口，从而提高代码的可重用性和可扩展性（泛型能确保编译时的类型安全）
         * 2）泛型编程的底层原理是通过泛型擦除来实现了。泛型类型在编译时进行类型检查，运行时泛型信息就会被擦除，变成原始类型，
         *    例如：对于泛型类List<T>，编译时检查类型是否正确，运行时就会由List<T>变为List<Object>
         * 3）泛型是一个编译时特性，在运行应用程序时，几乎没有任何影响。可通过javap -s class 查看字节码
         *
         *   备注：参考链接 https://blog.csdn.net/weixin_42508557/article/details/114865635
         */
    }

    /**
     * 场景3：泛型方法使用
     */
    @Test
    public void testGenericMethod() {
        GeneralMethod gMethod = new GeneralMethod();
        gMethod.generalMethod("1", 2, new GeneralMethod());

        /**
         * 输出结果：
         * 处理String类型数据中...
         * 处理Integer类型数据中...
         * 处理GeneralMethod类型数据中...
         *
         * 结果分析：
         * 1）GeneralMethod中的generalMethod接收泛型参数T，然后方法体中通过instanceof来判断具体类型再做逻辑处理
         *
         * 总结概括：
         * 1）方法参数接收泛型参数时，要在方法的签名上定义泛型参数，如 public <T> void generalMethod(T ... inputArray)
         */
    }
}
