package thinking.generic_type;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class CountedObject {
    private static long counter = 0;
    private final long id = counter ++;

    public long id() {
        return id;
    }

    public String toString() {
        return "CountedObject " + id;
    }
}
