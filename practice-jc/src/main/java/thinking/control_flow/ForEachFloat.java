package thinking.control_flow;

import java.util.Random;

/**
 * @author orange
 * @date 2024/6/3
 */
public class ForEachFloat {

    /**
     * 知识点（4.4）：Foreach语法
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        float f[] = new float[10];
        for (int i = 0; i < 10; i++) {
            f[i] = rand.nextFloat();
        }
        for (float x : f) {
            System.out.println(x);
        }
    }
}
