package com.csy.interview.offer_come.concurrent.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class DiscardOldestNPolicy implements RejectedExecutionHandler {

    private int discardNumber = 5;

    private List<Runnable> discardList = new ArrayList<>();

    public DiscardOldestNPolicy(int discardNumber) {
        this.discardNumber = discardNumber;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (executor.getQueue().size() > discardNumber) {
            executor.getQueue().drainTo(discardList, discardNumber);
            discardList.clear();
            if (!executor.isShutdown()) {
                executor.execute(r);
            }
        }
    }
}
