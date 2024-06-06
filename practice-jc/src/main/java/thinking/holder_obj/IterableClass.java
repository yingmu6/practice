package thinking.holder_obj;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author orange
 * @date 2024/6/5
 */
public class IterableClass implements Iterable<String> {
    /**
     * 知识点（11.13）：Foreach与迭代器
     */

    protected String[] words = ("And that is how " +
            "we know the Earth to be banana-shaped.").split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Iterable.super.spliterator();
    }

    public static void main(String[] args) {
        for (String s : new IterableClass()) {
            System.out.println(s + " ");
        }
    }
}
