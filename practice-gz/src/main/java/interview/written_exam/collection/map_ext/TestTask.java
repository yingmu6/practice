package interview.written_exam.collection.map_ext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chensy
 * @date 2023/8/26
 */
public class TestTask implements Runnable {

    private ConcurrentHashMap<Integer, Integer> map;

    public TestTask(ConcurrentHashMap<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前线程名：" + Thread.currentThread().getName() + "，map元素值：" + map.get(1)); //打印出线程信息，便于分析
            map.put(1, map.get(1) + 1); //将Map中key=1的元素依次取值累加
        }
    }
}
