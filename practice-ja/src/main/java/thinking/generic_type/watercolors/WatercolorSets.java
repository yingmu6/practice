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
public class WatercolorSets {
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
    }
}
