package thinking.inner_class;

/**
 * @author chensy
 * @date 2024/3/26
 */
public abstract class Event {

    private long eventTime; //事件发生事件

    protected final long delayTime;

    public Event(long start, long delayTime) {
        this.delayTime = delayTime;
        eventTime = start + delayTime; //计算出事件发生时间
    }

    public boolean ready() {
        return (System.currentTimeMillis() / 1000) >= eventTime;
    }

    public String appendTime(String str) {
        return str + "，时间：" + System.currentTimeMillis() / 1000;
    }

    public abstract void action();
}
