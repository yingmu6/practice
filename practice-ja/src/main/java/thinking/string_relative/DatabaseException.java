package thinking.string_relative;

/**
 * @author orange
 * @date 2024/6/4
 */
public class DatabaseException extends Exception {

    /**
     * 知识点（13.5.6）：String.format()
     */
    public DatabaseException(int transactionId, int queryID, String message) {
        super(String.format("(t%s, q%d) %s", transactionId, queryID, message));
    }

    public static void main(String[] args) {
        try {
            throw new DatabaseException(3, 7, "Write failed");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
