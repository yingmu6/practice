package thinking.collection_deep;

import net.mindview.util.*;

import java.util.Iterator;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class MapDataTest { //@TkY-Doing

    /**
     * 知识点（17.2.2）：Map生成器
     *
     * 关联点学习：
     * 1）LinkedHashMap源码阅读（Doing）
     */

    static class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {
        private int size = 9;
        private int number = 1;
        private char letter = 'A';
        public Pair<Integer, String> next() {
            return new Pair<>(
                    number++, "" + letter++); //字符累加，如'A'累加1，变为'B'
        }
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                public boolean hasNext() { return number < size; }
                public Integer next() { return number++; }
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    public static void main(String[] args) { //Doing
        print(MapData.map(new Letters(), 11));
        print(MapData.map(new CountingGenerator.Character()
        , new RandomGenerator.String(3), 8));
        print(MapData.map(new CountingGenerator.Character(), "Value", 6));
        print(MapData.map(new Letters(), new RandomGenerator.String(3)));
        print(MapData.map(new Letters(), "Pop"));

        /**
         * 输出结果：
         * {1=A, 2=B, 3=C, 4=D, 5=E, 6=F, 7=G, 8=H, 9=I, 10=J, 11=K}
         * {a=YNz, b=brn, c=yGc, d=FOW, e=ZnT, f=cQr, g=Gse, h=GZM}
         * {a=Value, b=Value, c=Value, d=Value, e=Value, f=Value}
         * {1=mJM, 2=RoE, 3=suE, 4=cUO, 5=neO, 6=EdL, 7=smw, 8=HLG}
         * {1=Pop, 2=Pop, 3=Pop, 4=Pop, 5=Pop, 6=Pop, 7=Pop, 8=Pop}
         *
         * 结果分析：
         * 1）Letterrs中的next方法，是将数字number、字母letter累加1，作为key、value值设置到Map中的，所以输出{1=A, 2=B...}
         */
    }
}
