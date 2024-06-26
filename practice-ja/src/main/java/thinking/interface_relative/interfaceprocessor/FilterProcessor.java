package thinking.interface_relative.interfaceprocessor;

import thinking.interface_relative.filters.*;

/**
 * @author orange
 * @date 2024/6/5
 */
public class FilterProcessor {

    static class FilterAdapter implements Processor {
        Filter filter;
        public FilterAdapter(Filter filter) {
            this.filter = filter;
        }
        public String name() { return filter.name(); }
        public Waveform process(Object input) {
            return filter.process((Waveform) input);
        }
    }

    public static void main(String[] args) {
        Waveform w = new Waveform();
        Apply.process(new FilterAdapter(new LowPass(1.0)), w);
        Apply.process(new FilterAdapter(new HighPass(2.0)), w);
        Apply.process(new FilterAdapter(new BrandPass(3.0, 4.0)), w);
    }
}
