package thinking.exception;

/**
 * @author orange
 * @date 2024/6/3
 */
public class FullConstructors {

    /**
     * 知识点（12.4）：创建自定义异常
     */

    static class MyException extends Exception {
        public MyException() {}
        public MyException(String msg) { super(msg); }
    }

    public static void f() throws MyException {
        System.out.println("Throwing MyException from f()");
        throw new MyException();
    }

    public static void g() throws MyException {
        System.out.println("Throwing MyException from g()");
        throw new MyException("Originated in g()");
    }

    public static void main(String[] args) {
        try {
            f();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }

        try {
            g();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }
    }
}
