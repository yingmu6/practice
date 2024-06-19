package thinking.holder_obj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author orange
 * @date 2024/6/5
 */
public class QueueDemo { //TkY-Done

    /**
     * 知识点（11.11）：Queue
     */
    public static void printQ(Queue queue) {
        while (queue.peek() != null) { //提取队首元素
            System.out.println(queue.remove() + " "); //提取并移除队首元素
        }
        System.out.println();
    }

    public static void main(String[] args) { //Done
        Queue<Integer> queue = new LinkedList<>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            queue.offer(rand.nextInt(i + 10)); //插入元素到队列中
        }
        printQ(queue);
        Queue<Character> qc = new LinkedList<>();
        for (char c : "Brontosaurus".toCharArray()) {
            qc.offer(c);
        }
        printQ(qc);

        /**
         * 输出结果：
         * 8
         * 1
         * 1
         * 1
         * 5
         * 14
         * 3
         * 1
         * 0
         * 1
         *
         * B
         * r
         * o
         * n
         * t
         * o
         * s
         * a
         * u
         * r
         * u
         * s
         *
         * 结果分析：
         * 1）第一个Queue中的元素是随机值组成的，第二个Queue中的元素是字符串"Brontosaurus"中的内容
         *    使用offer()：元素插入、peek()：元素读取，remove：读取元素并移除元素
         */
    }
}
