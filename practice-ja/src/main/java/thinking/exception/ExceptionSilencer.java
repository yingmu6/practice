package thinking.exception;

/**
 * @author orange
 * @date 2024/6/4
 */
public class ExceptionSilencer {

    /**
     * 知识点（12.8.3）：异常丢失
     */
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
            return;
        }
    }
}
