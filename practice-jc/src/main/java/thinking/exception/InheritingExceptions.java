package thinking.exception;

/**
 * @author orange
 * @date 2024/6/3
 */
public class InheritingExceptions {

    /**
     * 知识点（12.4）：创建自定义异常
     */

    static class SimpleException extends Exception {

    }

    public void f() throws SimpleException {
        System.out.println("Throw SimpleException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args) {
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (SimpleException e) {
            System.out.println("Caught it!");
        }
    }
}
