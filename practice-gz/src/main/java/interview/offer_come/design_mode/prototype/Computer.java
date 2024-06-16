package interview.offer_come.design_mode.prototype;

/**
 * @author chensy
 * @date 2024/3/14
 */
public class Computer implements Cloneable {
    /**
     * 浅复制
     */
    private String cpu;

    private String memory;

    private String disk;

    public Computer(String cpu, String memory, String disk) {
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
    }

    public Object clone() {
        try {
            return (Computer)super.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
