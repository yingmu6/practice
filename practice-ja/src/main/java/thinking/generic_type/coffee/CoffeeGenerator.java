package thinking.generic_type.coffee;

import net.mindview.util.Generator;

import java.util.Iterator;
import java.util.Random;

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

    public static void main(String[] args) { //Done_@pause-07/09
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next()); //随机取数组元素
        }
        for (Coffee c : new CoffeeGenerator(5)) {
            System.out.println(c);
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
         */
    }
}
