package thinking.init_destroy;

/**
 * @author chensy
 * @date 2024/4/19
 */
public class TerminationCondition {

    /**
     * 知识点（5.5.3）：终结条件
     */

    static class Book {
        boolean checkedOut = false;
        Book(boolean checkedOut) {
            this.checkedOut = checkedOut;
        }
        void checkIn() {
            this.checkedOut = false;
        }
        protected void finalize() {
            if (checkedOut) {
                System.out.println("Error：checkout out");

                // super.finalize(); 可以调用finalize()方法
            }
        }
    }

    public static void main(String[] args) {
        Book novel = new Book(true);
        novel.checkIn();
        new Book(true);
        System.gc();
    }
}
