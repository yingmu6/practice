package thinking.collection_deep;

import net.mindview.util.Deque;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class DequeTest<T> { //@TkY-Doing

    /**
     * 知识点：双向队列
     *
     * 关联点学习：
     * 1）LinkedList双向链表_源码阅读（Doing）
     * 2）数据结构中链表的CRUD操作处理（Doing）
     */

    static void fillTest(Deque<Integer> deque) {
        for (int i = 20; i < 27; i++) {
            deque.addFirst(i); //头插法
        }

        for (int i = 50; i < 55; i++) {
            deque.addLast(i); //尾插法
        }
    }

    public static void main(String[] args) {
        Deque<Integer> di = new Deque<>(); //Deque内部使用LinkedList实现
        fillTest(di);
        print(di);
        while (di.size() != 0) {
            printnb(di.removeFirst() + " ");
        }

        /**
         * 输出结果：
         * [26, 25, 24, 23, 22, 21, 20, 50, 51, 52, 53, 54]
         * 26 25 24 23 22 21 20 50 51 52 53 54
         *
         * 结果分析：
         * 1）20~26采用头插法，就是将元素依次放在第一个，插入序列如：[20], [21,20], [22,21,20]... 最终的序列和插入的序列相反
         * 2）50~54采用尾插法，就是将元素依次放在最后一个，插入序列如：[50], [50,51], [50,51,52]... 最终的序列和插入的序列相同
         */
    }
}
