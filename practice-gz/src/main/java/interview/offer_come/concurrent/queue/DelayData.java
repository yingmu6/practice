package interview.offer_come.concurrent.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class DelayData implements Delayed {

    private Integer number;

//    private long delayTime = 50000;
    private long delayTime = 5000;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.delayTime;
    }

    @Override
    public int compareTo(Delayed o) {
        DelayData compare = (DelayData) o;
        return this.number.compareTo(compare.getNumber());
    }
}
