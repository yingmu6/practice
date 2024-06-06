package thinking.interface_relative.filters;

/**
 * @author orange
 * @date 2024/6/5
 */
public class HighPass extends Filter{
    double cutoff;
    public HighPass(double cutoff) { this.cutoff = cutoff; }
    public Waveform process(Waveform input) { return input; }
}
