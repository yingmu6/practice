package thinking.exception;

/**
 * @author orange
 * @date 2024/6/3
 */
public class RethrowNew {

    /**
     * 知识点（12.6.2）：重新抛出异常
     */
    static class OneException extends Exception {
        public OneException(String s) { super(s); }
    }

    static class TwoException extends Exception {
        public TwoException(String s) { super(s); }
    }

    public static void f() throws OneException {
        System.out.println("originating the exception in f()");
        throw new OneException("throw fron f()");
    }

    public static void main(String[] args) {
        try {
            try {
                f();
            } catch (OneException e) {
                System.out.println("Caught in inner try，e.printStackTrace()");
                e.printStackTrace(System.out);
                throw new TwoException("from inner try");
            }
        } catch (TwoException e) {
            System.out.println("Caught in outer try，e.printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
}
