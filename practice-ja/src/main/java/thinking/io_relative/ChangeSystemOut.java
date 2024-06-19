package thinking.io_relative;

import java.io.PrintWriter;

/**
 * @author orange
 * @date 2024/6/7
 */
public class ChangeSystemOut {

    /**
     * 知识点（18.8.2）：将System.out转换成PrintWriter
     */
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello，world");
    }
}
