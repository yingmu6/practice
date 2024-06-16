package interview.offer_come.concurrent.thread;

import java.util.concurrent.Callable;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class MyCallable implements Callable<String> {

    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        return this.name;
    }
}
