package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class LostMessage {

    /**
     * 知识点（12.8.3）：缺憾_异常丢失
     */
    static class VeryImportantException extends Exception {
        public String toString() {
            return "A very important exception!";
        }
    }

    static class HoHumException extends Exception {
        public String toString() {
            return "A trivial exception";
        }
    }

    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } finally {
                lm.dispose();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
