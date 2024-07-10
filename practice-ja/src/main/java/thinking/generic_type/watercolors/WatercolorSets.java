package thinking.generic_type.watercolors;

import java.util.EnumSet;
import java.util.Set;

import static net.mindview.util.Print.print;
import static net.mindview.util.Sets.*;
import static thinking.generic_type.watercolors.Watercolors.*;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class WatercolorSets { //@TkY-Doing @pause-07/10
    public static void main(String[] args) {
        Set<Watercolors> set1 =
                EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> set2 =
                EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
        print("set1: " + set1);
        print("set2: " + set2);
        print("union(set1, set2): " + union(set1, set2));

        Set<Watercolors> subset = intersection(set1, set2);
        print("intersection(set1, set2): " + subset);
        print("diffence(set1, subset): " + difference(set1, subset));
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
         */
    }
}
