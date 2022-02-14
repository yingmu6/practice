package relative.design.adapter;

import java.util.concurrent.Callable;

/**
 * @author : chensy
 * Date : 2020/10/19 下午11:50
 */
public class TaskAdapter implements Runnable{
    private Callable callable;
    public TaskAdapter(Callable callable) {
        this.callable = callable;
    }
    @Override
    public void run() {
        try {
            callable.call();
            System.out.println("TaskAdapter");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
