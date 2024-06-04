package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class WithFinally {

    /**
     * 知识点（12.8.1）：finally用途
     */
    static Switch sw = new Switch();

    public static void main(String[] args) {
        try {
            sw.on();
            Switch.OnOffSwitch.f();
        } catch (Switch.OnOffException1 e) {
            System.out.println("OnOffException1");
        } catch (Switch.OnOffException2 e) {
            System.out.println("OnOffException2");
        } finally {
            sw.off();
        }
    }
}
