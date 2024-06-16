package interview.offer_come.concurrent.queue;

/**
 * @author chensy
 * @date 2024/3/28
 */
public class Data implements Comparable<Data> {

    private String id;

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int compareTo(Data o) {
        return this.number.compareTo(o.getNumber());
    }
}
