package thinking.interface_relative.filters;

/**
 * @author orange
 * @date 2024/6/5
 */
public class BrandPass extends Filter {
    double lowCuttoff, highCutoff;
    public BrandPass(double lowCuttoff, double highCutoff) {
        this.lowCuttoff = lowCuttoff;
        this.highCutoff = highCutoff;
    }
}
