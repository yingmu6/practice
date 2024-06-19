package thinking.array_relative;

import java.util.Arrays;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/5/28
 */
public class ArrayOptions {

    /**
     * 知识点（16.2）：数组是第一级对象
     *
     * 知识点概括：
     * 1）
     */
    public static void main(String[] args) {
        ContainerComparision.BerylliumSphere[] a;
        ContainerComparision.BerylliumSphere[] b = new ContainerComparision.BerylliumSphere[5];
        print("b：" + Arrays.toString(b));
        ContainerComparision.BerylliumSphere[] c = new ContainerComparision.BerylliumSphere[4];
        for (int i = 0; i < c.length; i++) {
            if (c[i] == null) {
                c[i] = new ContainerComparision.BerylliumSphere();
            }
        }
        ContainerComparision.BerylliumSphere[] d = {
          new ContainerComparision.BerylliumSphere(),
          new ContainerComparision.BerylliumSphere(),
          new ContainerComparision.BerylliumSphere()
        };

        a = new ContainerComparision.BerylliumSphere[] {
          new ContainerComparision.BerylliumSphere(),
          new ContainerComparision.BerylliumSphere()
        };

        print("a.length = " + a.length);
        print("b.length = " + b.length);
        print("c.length = " + c.length);
        print("d.length = " + d.length);
        a = d;
        print("a.length = " + a.length);

        int[] e;
        int[] f = new int[5];
        print("f：" + Arrays.toString(f));
        int[] g = new int[4];
        for (int i = 0; i < g.length; i++) {
            g[i] = i * i;
        }
        int[] h = { 11, 47, 93 };

        // print("e.length = " + e.length); //编译错误，e没有被初始化
        print("f.length = " + f.length);
        print("g.length = " + g.length);
        print("h.length = " + h.length);
        e = h;
        print("e.length = " + e.length);
        e = new int[] { 1, 2 };
        print("e.length = " + e.length);
    }
}
