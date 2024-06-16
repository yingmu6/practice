package interview.offer_come.design_mode.prototype;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Disk implements Cloneable {
    private String ssd;

    private String hhd;

    public Disk(String ssd, String hhd) {
        this.ssd = ssd;
        this.hhd = hhd;
    }

    public Object clone() {
        try {
            return (Disk) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
