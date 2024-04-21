package thinking.collection_deep;

import net.mindview.util.*;

import java.util.Iterator;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class MapDataTest {

    /**
     * 知识点（17.2.2）：Map生成器
     */

    static class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {
        private int size = 9;
        private int number = 1;
        private char letter = 'A';
        public Pair<Integer, String> next() {
            return new Pair<>(
                    number++, "" + letter++);
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

    public static void main(String[] args) {
        print(MapData.map(new Letters(), 11));
        print(MapData.map(new CountingGenerator.Character()
        , new RandomGenerator.String(3), 8));
        print(MapData.map(new CountingGenerator.Character(), "Value", 6));
        print(MapData.map(new Letters(), new RandomGenerator.String(3)));
        print(MapData.map(new Letters(), "Pop"));
    }
}
