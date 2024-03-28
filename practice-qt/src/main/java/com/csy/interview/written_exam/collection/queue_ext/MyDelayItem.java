package com.csy.interview.written_exam.collection.queue_ext;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2023/9/1
 */
public class MyDelayItem implements Delayed {

    private long liveTime; //存活的时间

    private long leaveTime; //出队的时间 = 创建时间 + 存活时间

    public MyDelayItem (long liveTime) {
        this.liveTime = liveTime;
        this.leaveTime = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(liveTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(leaveTime - System.currentTimeMillis(), unit); //延迟时间 = 出队时间 - 当前时间
    }

    @Override
    public int compareTo(Delayed o) { //实现Delayed接口，需要实现compareTo方法，且方法内使用getDelay方法的返回值作比较
        if (o == null) {
            return -1;
        }

        if (o == this) {
            return 0;
        }

        long diff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS); //使用延迟时间作比较
        return diff > 0 ? 1 : diff == 0 ? 0 : -1;
    }

    @Override
    public String toString() {
        return "{存活时间：" + String.valueOf(liveTime) + "，出队时间：" + String.valueOf(leaveTime) + "}";
    }
}
