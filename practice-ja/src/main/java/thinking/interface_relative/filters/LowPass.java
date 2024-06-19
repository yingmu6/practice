package thinking.interface_relative.filters;

/**
 * @author orange
 * @date 2024/6/5
 */
public class LowPass extends Filter {
    double cutoff;
    public LowPass(double cutoff) { this.cutoff = cutoff; }
    public Waveform process(Waveform input) {
        return input;
    }
}
