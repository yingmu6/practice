package thinking.holder_obj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author orange
 * @date 2024/6/5
 */
public class QueueDemo {

    /**
     * 知识点（11.11）：Queue
     */
    public static void printQ(Queue queue) {
        while (queue.peek() != null) {
            System.out.println(queue.remove() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            queue.offer(rand.nextInt(i + 10));
        }
        printQ(queue);
        Queue<Character> qc = new LinkedList<>();
        for (char c : "Brontosaurus".toCharArray()) {
            qc.offer(c);
        }
        printQ(qc);
    }
}
