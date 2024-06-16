package interview.written_exam.collection.queue_ext;

/**
 * @author chensy
 * @date 2023/8/28
 */
public class PriorityProduct implements Comparable<PriorityProduct> {

    private int priority;

    private String productName;

    public PriorityProduct(int priority, String productName) {
        this.priority = priority;
        this.productName = productName;
    }

    @Override
    public int compareTo(PriorityProduct o) {
        if (o == null) {
            return -1;
        }

        if (o == this) {
            return 0;
        }
        return o.priority - this.priority;
    }

    public String toString() {
        return "{priority:" + priority + ",name=" + this.productName;
    }
}
