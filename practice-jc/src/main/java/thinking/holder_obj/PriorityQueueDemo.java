package thinking.holder_obj;

import java.util.*;

/**
 * @author orange
 * @date 2024/6/5
 */
public class PriorityQueueDemo { //@TkY-Doing

    /**
     * 知识点（11.11.1）：PriorityQueue
     */

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            priorityQueue.offer(rand.nextInt(i + 10));
        }
        QueueDemo.printQ(priorityQueue);
        List<Integer> ints = Arrays.asList(25, 22, 20,
                18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<>(ints);
        QueueDemo.printQ(priorityQueue);

        priorityQueue = new PriorityQueue<>(
                ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        QueueDemo.printQ(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ =
                new PriorityQueue<>(strings);
        QueueDemo.printQ(stringPQ);
        stringPQ = new PriorityQueue<>(
                strings.size(), Collections.reverseOrder());
        stringPQ.addAll(strings);
        QueueDemo.printQ(stringPQ);

        Set<Character> charSet = new HashSet<>();
        for (char c : fact.toCharArray()) {
            charSet.add(c);
        }
        PriorityQueue<Character> characterPQ =
                new PriorityQueue<>();
        QueueDemo.printQ(characterPQ);

        /**
         * 输出结果：
         * 0
         * 1
         * 1
         * 1
         * ...
         *
         * 1
         * 1
         * 2
         * 3
         * ...
         *
         * A
         * A
         * B
         * ...
         *
         * 结果分析：
         * 1）
         */
    }
}
