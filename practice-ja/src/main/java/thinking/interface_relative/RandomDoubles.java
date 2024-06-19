package thinking.interface_relative;

import java.util.Random;

/**
 * @author orange
 * @date 2024/6/5
 */
public class RandomDoubles {

    /**
     * 知识点（9.6）：适配接口
     */
    private static Random rand = new Random(47);
    public double next() { return rand.nextDouble(); }

    public static void main(String[] args) {
        RandomDoubles rd = new RandomDoubles();
        for (int i = 0; i < 7; i++) {
            System.out.println(rd.next() + " ");
        }
    }
}
