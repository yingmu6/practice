package thinking.collection_deep;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class CountingMapDataSelf extends AbstractMap<Integer, String> { //自定义Map（实现entry()逻辑、以及自定义的Entry）
    private int size;
    private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

    public CountingMapDataSelf(int size) {
        if (size < 0) {
            this.size = 0;
        }
        this.size = size;
    }

    public Set<Map.Entry<Integer, String>> entrySet() { //获取Entry集合（AbstractMap中会回调该方法）
        Set<Map.Entry<Integer, String>> entries = new LinkedHashSet();

        for(int i = 0; i < this.size; ++i) { //自定义处理逻辑
            entries.add(new CountingMapDataSelf.Entry(i));
        }

        return entries;
    }

    public static void main(String[] args) {
        CountingMapDataSelf dataSelf = new CountingMapDataSelf(10);

        // 方式1：输出对象，就会调用对象的toString()方法，最终进入AbstractMap的toString()方法
        System.out.println(dataSelf);

        /**
         * 方式2：没有通过System.out.println(obj)输出对象，这时通过debug调试时，相当于debug调用toString方法，
         *       但这个是idea的debug另起的线程调用的toString()方法，所以与当前线程不在同一个线程，进入不了toString方法的断点
         */
        // System.out.println(11);

        /**
         * 输出结果：
         * {0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0}
         *
         * 结果分析：
         * 1）调用System.out.println(obj)输出对象时，就会调用对象的toString()方法，因为CountingMapDataSelf没有重写toString()方法，
         *    所以就会进入AbstractMap的toString()方法。而该方法中就会调用子类实现的entrySet()获取到Entry集合。
         *
         * 2）有了Entry集合后，就可以调用Entry实现类的getKey()、getValue()来获取到Map中Entry的key、value值
         *
         * 问题点答疑：
         * 1）CountingMapDataSelf中的key、value是什么时候设置的？
         *    解答：CountingMapDataSelf中静态内部类Entry，并没有直接定义成员变量key、value，而是通过对index的逻辑处理而产生key、value
         *         虽然不能够直观理解，但对于父类来说，直观回调实现类的getKey()、getValue()方法，不管是不是有对应的成员变量。
         *
         * 2）静态内部类Entry中的getKey和getValue方法是什么时候被调用的？
         *    解答：在AbstractMap中有许多方法回调了getKey()、getValue()，如：containsKey(key)、keySet()等方法。
         */
    }

    private static class Entry implements Map.Entry<Integer, String> {
        int index;

        Entry(int index) {
            this.index = index;
        }

        public boolean equals(Object o) {
            return Integer.valueOf(this.index).equals(o);
        }

        public Integer getKey() { //将index值作为key（AbstractMap中会回调该方法）
            return this.index;
        }

        public String getValue() { //将chars数组中的元素值与index进行拼接，形成value值，如A0、B1等
            return CountingMapDataSelf.chars[this.index % CountingMapDataSelf.chars.length] + Integer.toString(this.index / CountingMapDataSelf.chars.length);
        }

        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        public int hashCode() {
            return Integer.valueOf(this.index).hashCode();
        }
    }
}
