package thinking.interface_relative.filters;

/**
 * @author orange
 * @date 2024/6/5
 */
public class Waveform {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Waveform " + id; }
}
