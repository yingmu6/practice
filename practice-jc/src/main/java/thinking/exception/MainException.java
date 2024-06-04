package thinking.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author orange
 * @date 2024/6/4
 */
public class MainException {

    /**
     * 知识点（12.12.3）：把异常传递给控制台
     */
    public static void main(String[] args) throws Exception {
        FileInputStream file =
                new FileInputStream("MainException.java");
        file.close();
    }
}
