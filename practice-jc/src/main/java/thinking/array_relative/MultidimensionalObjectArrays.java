package thinking.array_relative;

import java.util.Arrays;

/**
 * @author orange
 * @date 2024/5/28
 */
public class MultidimensionalObjectArrays {

    /**
     * 知识点（16.4）：
     */

    public static void main(String[] args) {
        ContainerComparision.BerylliumSphere[][] spheres = {
                { new ContainerComparision.BerylliumSphere(), new ContainerComparision.BerylliumSphere() },
                { new ContainerComparision.BerylliumSphere(), new ContainerComparision.BerylliumSphere(),
                  new ContainerComparision.BerylliumSphere(), new ContainerComparision.BerylliumSphere() },
                { new ContainerComparision.BerylliumSphere(), new ContainerComparision.BerylliumSphere(),
                  new ContainerComparision.BerylliumSphere(), new ContainerComparision.BerylliumSphere(),
                  new ContainerComparision.BerylliumSphere(), new ContainerComparision.BerylliumSphere(),
                  new ContainerComparision.BerylliumSphere(), new ContainerComparision.BerylliumSphere() },
        };
        System.out.println(Arrays.deepToString(spheres));
    }
}
