package thinking.concurrent.case2;

import java.util.concurrent.Callable;

/**
 * @author chensy
 * @date 2024/4/13
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}
