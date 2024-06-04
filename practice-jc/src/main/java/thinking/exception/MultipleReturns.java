package thinking.exception;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/4
 */
public class MultipleReturns {

    /**
     * 知识点（12.8.2）：在return中使用finally
     */

    public static void f(int i) {
        print("Initiazation that required cleanup");

        try {
            print("Point 1");
            if (i == 1) return;
            print("Point 2");
            if (i == 2) return;
            print("Point 3");
            if (i == 3) return;
            print("End");
            return;
        } finally {
            print("Performing cleanup");
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            f(i);
        }
    }
}
