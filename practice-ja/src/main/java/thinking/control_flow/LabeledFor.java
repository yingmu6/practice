package thinking.control_flow;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/3
 */
public class LabeledFor {

    /**
     * 知识点（4.7）：循环中跳转
     */
    public static void main(String[] args) {
        int i = 0;
        outer:
        for(; true ;) {
            inner:
            for(; i < 10; i++) {
                print("i = " + i);
                if (i == 2) {
                    print("continue");
                    continue;
                }
                if (i == 3) {
                    print("break");
                    i++;
                    break;
                }
                if (i == 7) {
                    print("continue outer");
                    i++;
                    continue outer;
                }
                if (i == 8) {
                    print("break outer");
                    continue inner;
                }
                for (int k = 0; k < 5; k++) {
                    if (k == 3) {
                        print("continue inner");
                        continue inner;
                    }
                }
            }
        }
    }
}
