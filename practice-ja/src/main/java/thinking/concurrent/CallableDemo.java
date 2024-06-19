package thinking.concurrent;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class CallableDemo {

    static class TaskWithResult implements Callable<String> {

        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            return "result of TaskWithResult " + id;
        }
    }

    public static void main(String[] args) { //从任务中产生返回值
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        }
    }
}
