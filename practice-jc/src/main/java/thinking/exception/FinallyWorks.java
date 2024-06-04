package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class FinallyWorks {

    /**
     * 知识点（12.8）：使用finally进行清理
     */
    static class ThreeException extends Exception {}

    static int count = 0;

    public static void main(String[] args) {
        while(true) {
            try {
                if (count++ == 0) {
                    throw new ThreeException();
                }
                System.out.println("No exception");
            } catch (ThreeException e) {
                System.out.println("ThreeException");
            } finally {
                System.out.println("In finally clause");
                if (count == 2) {
                    break;
                }
            }
        }
    }
}
