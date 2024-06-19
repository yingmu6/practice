package thinking.exception;

/**
 * @author orange
 * @date 2024/6/3
 */
public class WhoCalled {

    /**
     * 知识点（12.6.1）：栈轨迹
     */
    static void f() {
        try {
            throw new Exception();
        } catch (Exception e) {
            for (StackTraceElement ste : e.getStackTrace()) {
                System.out.println(ste.getMethodName());
            }
        }
    }

    static void g() { f(); }
    static void h() { g(); }

    public static void main(String[] args) {
        f();
        System.out.println("--------------------");
        g();
        System.out.println("--------------------");
        h();
    }
}
