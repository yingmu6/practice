package thinking.generic_type.watercolors;

import org.junit.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static net.mindview.util.Print.print;
import static net.mindview.util.Sets.*;
import static thinking.generic_type.watercolors.Watercolors.*;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class WatercolorSets { //@TkY-Doing

    /**
     * 知识点：
     *
     * 关联点学习：
     * 1）List、Set初始化的多种形式实践（Doing）
     * 2）集合的特性以及交集/并集/补集等了解（Doing）
     *
     */

    public static void main(String[] args) { //Done
        Set<Watercolors> set1 =
                EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> set2 =
                EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
        print("set1: " + set1);
        print("set2: " + set2);
        print("union(set1, set2): " + union(set1, set2)); //求并集

        Set<Watercolors> subset = intersection(set1, set2);
        print("intersection(set1, set2): " + subset); //求交集
        print("diffence(set1, subset): " + difference(set1, subset)); //求差集
        print("diffence(set2, subset): " + difference(set2, subset));
        print("complement(set1, set2): " + complement(set1, set2));

        /**
         * 输出结果：
         * set1: [BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER, VIOLET, CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE, PERMANENT_GERRN, VIRIDIAN_HUE]
         * set2: [CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE, PERMANENT_GERRN, VIRIDIAN_HUE, SAP_GERRN, YELLOW_OCHRE, BURNT_SIEMMA, RAW_UMBER, BURNT_UMBER]
         * union(set1, set2): [YELLOW_OCHRE, RAW_UMBER, CERULEAN_BLUE_HUE, PHTHALO_BLUE, SAP_GERRN, BURNT_SIEMMA, CRIMSON, COBALT_BLUE_HUE, MAGENTA, VIOLET, BRILLIANT_RED, ULTRAMARINE, VIRIDIAN_HUE, ROSE_MADDER, BURNT_UMBER, PERMANENT_GERRN]
         * intersection(set1, set2): [ULTRAMARINE, CERULEAN_BLUE_HUE, VIRIDIAN_HUE, PHTHALO_BLUE, COBALT_BLUE_HUE, PERMANENT_GERRN]
         * diffence(set1, subset): [MAGENTA, VIOLET, BRILLIANT_RED, ROSE_MADDER, CRIMSON]
         * diffence(set2, subset): [YELLOW_OCHRE, RAW_UMBER, BURNT_UMBER, SAP_GERRN, BURNT_SIEMMA]
         * complement(set1, set2): [YELLOW_OCHRE, RAW_UMBER, SAP_GERRN, BURNT_SIEMMA, CRIMSON, MAGENTA, VIOLET, BRILLIANT_RED, ROSE_MADDER, BURNT_UMBER]
         *
         * 结果分析：
         * 1）EnumSet.range(E from, E to)可以获取指定枚举区间的枚举组成的集合
         *
         * 2）根据集合提供的能力，可通过交集、并集、补集等获取到对应的集合
         */
    }

    /**
     * 新增场景：用简单值代替集合元素，便于直观判断集合运算
     */
    @Test
    public void testSet() { //Done
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(4);
        set2.add(5);

        print("set1: " + set1);
        print("set2: " + set2);
        print("union(set1, set2): " + union(set1, set2)); //求并集（使用Set的addAll方法）

        Set<Integer> subset = intersection(set1, set2);
        print("intersection(set1, set2): " + subset); //求交集（使用Set的retainAll方法）
        print("diffence(set1, subset): " + difference(set1, subset)); //求差集（使用Set的removeAll方法）
        print("diffence(set2, subset): " + difference(set2, subset));
        print("complement(set1, set2): " + complement(set1, set2)); //求补集

        /**
         * 输出结果：
         * set1: [1, 2, 3]
         * set2: [2, 4, 5]
         * union(set1, set2): [1, 2, 3, 4, 5]
         * intersection(set1, set2): [2]
         * diffence(set1, subset): [1, 3]
         * diffence(set2, subset): [4, 5]
         * complement(set1, set2): [1, 3, 4, 5]
         *
         * 结果分析：
         * 1）封装了工具方法，使用Set的addAll、retainAll、removeAll等方法来处理
         *    并集、交集、补集等业务。直接使用Set的提供的方法，理解不太直观，所以写个工具方法
         *    进行封装，会更容易见名知义。
         *
         * 2）complement(set1, set2)求补集：
         *    内部逻辑为：difference(union(a, b), intersection(a, b))
         *    即先求集合a与b的并集，然后再求a与b的交集，最后求并集和交集的差集
         */
    }
}
