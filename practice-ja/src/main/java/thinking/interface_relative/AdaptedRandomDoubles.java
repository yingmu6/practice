package thinking.interface_relative;

import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * @author orange
 * @date 2024/6/5
 */
public class AdaptedRandomDoubles extends RandomDoubles implements Readable {

    /**
     * 知识点（9.6）：适配接口
     */

    private int count;
    public AdaptedRandomDoubles(int count) {
        this.count = count;
    }

    public int read(CharBuffer cb) {
        if (count-- == 0) {
            return -1;
        }

        String result = Double.toString(next()) + " ";
        cb.append(result);
        return result.length();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new AdaptedRandomDoubles(47));
        while (s.hasNextDouble()) {
            System.out.println(s.nextDouble() + " ");
        }
    }
}
