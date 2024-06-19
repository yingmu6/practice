package thinking.string_relative;

import java.util.Scanner;

/**
 * @author orange
 * @date 2024/6/4
 */
public class ScannerDelimiter {

    /**
     * 知识点（13.7.1）：Scanner定界符
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner("12, 42, 78, 99,42");
        scanner.useDelimiter("\\s, \\s*");
        while (scanner.hasNext()) {
            System.out.println(scanner.nextInt());
        }
    }
}
