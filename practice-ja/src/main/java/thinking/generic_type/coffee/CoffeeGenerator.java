package thinking.generic_type.coffee;

import net.mindview.util.Generator;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.*;

/**
 * @author chensy
 * @date 2024/5/17
 */
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {


    /**
     * 知识点：
     *
     * 关联点学习：
     * 1）Random产生随机值学习&实践（Doing）
     * 2）java的ForEach语法格式学习（Doing）
     *
     */

    private Class[] types = { Latte.class, Mocha.class,
      Cappuccino.class, Americano.class, Breve.class };
    private static Random rand = new Random(47);

    public CoffeeGenerator() {}
    private int size = 0;
    public CoffeeGenerator(int sz) {
        size = sz;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance(); //产生随机值作为下标，随机取数组中元素
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) { //Done
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next()); //随机取数组元素
        }
        for (Coffee c : new CoffeeGenerator(5)) {
            System.out.println(c);
        }

        // 新增场景：forEach遍历集合
        List<String> testList = Arrays.asList("zhang","li", "wang");
        for (String str : testList) {
            System.out.println(str);
        }

        /**
         * 输出结果：
         * Americano 0
         * Latte 1
         * Americano 2
         * Mocha 3
         * Mocha 4
         * Breve 5
         * Americano 6
         * Latte 7
         * Cappuccino 8
         * Cappuccino 9
         *
         * 结果分析：
         * 1）第一个for循环时，是直接调用CoffeeGenerator的next()随机获取到数组元素
         *
         * 2）第一个循环是forEach语法。先创建了CoffeeGenerator实例，因为继承了Iterator，
         *   所以会调用其内部的iterator()方法，获取到Iterator的实例，此处为CoffeeIterator
         *   后面每次循环，都由系统主动调用，会先调用hasNext()判断是否有元素，然后再调用next()获取到元素
         *
         * 3）使用forEach遍历集合时，通过调试看，会先调用AbstractList的iterator()方法找到迭代器
         *    然后再调用迭代器的hasNext()进行判断，最后调用next()获取到元素
         *    （本质上：forEach遍历集合和遍历迭代器，内部原理都是一样的，最终都是遍历迭代器）
         *
         */
    }

    /**
     * 新增场景：forEach遍历集合
     */
//    @Test
//    public void testForEach() {
//        List<String> testList = Arrays.asList("zhang","li", "wang");
//        for (String str : testList) {
//            System.out.println(str);
//        }
//
//        /**
//         * 结果分析：
//         * 1）此处的junit只支持一个构造方法，但目前有两个构造方法，所以报参数异常
//         *   java.lang.IllegalArgumentException: Test class can only have one constructor
//         */
//    }
}
