package thinking.collection_deep;

import net.mindview.util.Deque;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class DequeTest<T> {

    /**
     * 知识点：双向队列
     */

    static void fillTest(Deque<Integer> deque) {
        for (int i = 20; i < 27; i++)
            deque.addFirst(i);
        for (int i = 50; i < 55; i++)
            deque.addLast(i);
    }

    public static void main(String[] args) {
        Deque<Integer> di = new Deque<>();
        fillTest(di);
        print(di);
        while (di.size() != 0)
            printnb(di.removeFirst() + " ");
    }
}
