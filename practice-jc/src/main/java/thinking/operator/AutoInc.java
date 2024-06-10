package thinking.operator;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/8
 */
public class AutoInc {

    /**
     * 知识点（3.6）：自动递增和递减
     */
    public static void main(String[] args) {
        int i = 1;
        print("i：" + i);
        print("++i：" + ++i);
        print("i++：" + i++);
        print("i：" + i);
        print("--i：" + --i);
        print("i--：" + i--);
        print("i ：" + i);
    }
}
