package thinking.io_relative;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author orange
 * @date 2024/6/7
 */
public class Echo {

    /**
     * 知识点（18.8）：标准I/O
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String s;
        while ((s = stdin.readLine()) != null && s.length() != 0) {
            System.out.println(s);
        }
    }
}
