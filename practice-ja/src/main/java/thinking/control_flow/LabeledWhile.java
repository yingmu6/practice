package thinking.control_flow;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/3
 */
public class LabeledWhile {

    /**
     * 知识点（4.7）：循环中跳转
     */
    public static void main(String[] args) {
        int i = 0;
        outer:
        while (true) {
            print("Outer while loop");
            while (true) {
                i++;
                print("i = " + i);
                if (i == 1) {
                    print("continue");
                    continue;
                }
                if (i == 3) {
                    print("continue outer");
                    continue outer;
                }
                if (i == 5) {
                    print("break");
                }
                if (i == 7) {
                    print("break outer");
                    break outer;
                }
            }
        }
    }
}
