package thinking.exception;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class Switch {

    /**
     * 知识点（12.8.1）：finally用途
     */
    private boolean state = false;
    public boolean read() { return state; }
    public void on() { state = true; print(this); }
    public void off() { state = false; print(this); }
    public String toString() { return state ? "on" : "off"; }

    static public class OnOffException1 extends Exception {}
    static public class OnOffException2 extends Exception {}

    static public class OnOffSwitch {
        private static Switch sw = new Switch();
        public static void f() throws OnOffException1, OnOffException2 {}

        public static void main(String[] args) {
            try {
                sw.on();
                f();
                sw.off();
            } catch (OnOffException1 e) {
                System.out.println("OnOffException1");
                sw.off();
            } catch (OnOffException2 e) {
                System.out.println("OnOffException2");
                sw.off();
            }
        }
    }
}
