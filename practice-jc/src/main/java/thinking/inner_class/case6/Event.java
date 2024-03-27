package thinking.inner_class.case6;

/**
 * @author chensy
 * @date 2024/3/26
 */
public abstract class Event {

    private long eventTime;

    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    public void start() {
        eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready() {
        return System.nanoTime() >= eventTime;
    }

    public abstract void action();
}
