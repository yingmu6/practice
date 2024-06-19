package thinking.operator;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/11
 */
public class CastingNumbers {

    /**
     * 知识点（3.15.1）：截尾和舍入
     */
    public static void main(String[] args) {
        double above = 0.7, below = 0.4;
        float fabove = 0.7f, fbelow = 0.4f;
        print("(int)above：" + (int)above);
        print("(int)below：" + (int)below);
        print("(int)fabove：" + (int)fabove);
        print("(int)fbelow：" + (int)fbelow);
    }
}
