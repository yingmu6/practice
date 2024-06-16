package interview.offer_come.data_struct;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class Queue<E> {

    private Object[] data = null;

    private int maxSize;

    private int front;

    private int rear;

    public Queue() {
        this(10);
    }

    public Queue(int initialSize) {
        if (initialSize > 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化大小不能小于0：" + initialSize);
        }
    }

    public boolean add(E e) {
        if (rear == maxSize) {
            throw new RuntimeException("队列已满，无法插入新的元素！");
        } else {
            data[rear++] = e;
            return true;
        }
    }

    public E poll() {
        if (empty()) {
            throw new RuntimeException("空队列异常");
        } else {
            E value = (E) data[front];
            data[front++] = null;
            return value;
        }
    }

    public E peek() {
        if (empty()) {
            throw new RuntimeException("空队列异常！");
        } else {
            return (E) data[front];
        }
    }

    public boolean empty() {
        return data.length == 0;
    }
}
