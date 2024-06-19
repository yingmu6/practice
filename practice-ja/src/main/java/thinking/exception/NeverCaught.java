package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class NeverCaught {

    /**
     * 知识点（12.7.1）：特例RuntimeException
     */

    static void f() {
        throw new RuntimeException("From f()");
    }

    static void g() {
        f();
    }

    public static void main(String[] args) {
        g();
    }
}
