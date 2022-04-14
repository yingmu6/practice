package relative.basic.map_test;

import java.util.Map;

/**
 * @author chensy
 * @date 2022/4/14
 */
public class DefaultValueTest {

    /**
     * 成员变量的默认值
     * 1）对象类型和封装类型都为null
     * 2）基本数据类型，会有默认值
     */
    private Map<String, String> map;

    private Integer a;

    private int b;

    private double c;

    public static void main(String[] args) {
        DefaultValueTest test = new DefaultValueTest();
        System.out.println(test.map); //输出null
        System.out.println(test.a); //输出null
        System.out.println(test.b); //输出0
        System.out.println(test.c); //输出0.0
    }
}
