package thinking.generic_type;

import java.util.Iterator;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class IterableFibonacci extends Fibonacci implements Iterable<Integer> {

    /**
     * 知识点（15.3）：泛型接口
     */
    private int n;

    public IterableFibonacci(int count) {
        n = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Integer next() {
                n--;
                return IterableFibonacci.this.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        for (int i : new IterableFibonacci(18)) {
            System.out.println(i + " ");
        }
    }
}
