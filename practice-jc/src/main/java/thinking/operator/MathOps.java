package thinking.operator;

import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/7
 */
public class MathOps {

    /**
     * 知识点（3.5）：算术操作符
     */
    public static void main(String[] args) {
        Random rand = new Random(47);
        int i, j, k;
        j = rand.nextInt(100) + 1;
        print("j：" + j);
        k = rand.nextInt(100) + 1;
        print("k：" + k);
        i = j + k;
        print("j + k : " + i);
        i = j - k;
        print("j - k：" + i);
        i = k / j;
        print("k / j:" + i);
        i = k * j;
        print("k * j:" + i);
        i = k % j;
        print("k % j:" + i);
        j %= k;
        print("j %= k:" + j);
        float u, v, w;
        v = rand.nextFloat();
        print("v ：" + v);
        w = rand.nextFloat();
        print("w ：" + w);
        w = rand.nextFloat();
        u = v + w;
        print("v + w：" + u);
        u = v - w;
        print("v - w: " + u);
        u = v * w;
        print("v * w: " + u);
        u = v / w;
        print("v / w: " + u);
        u += v;
        print("v += w: " + u);
        u -= v;
        print("v -= w: " + u);
        u *= v;
        print("v *= w: " + u);
        u -= v;
        print("v - w: " + u);
    }
}
