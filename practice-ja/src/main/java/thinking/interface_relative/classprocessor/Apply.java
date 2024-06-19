package thinking.interface_relative.classprocessor;

import java.util.Arrays;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/5
 */
public class Apply {

    /**
     * 知识点（9.3）：安全解耦
     */
    static class Processor {
        public String name() {
            return getClass().getSimpleName();
        }
        Object process(Object input) { return input; }
    }

    static class Upcase extends Processor {
        String process(Object input) {
            return ((String) input).toUpperCase();
        }
    }

    static class Downcase extends Processor {
        String process(Object input) {
            return ((String)input).toLowerCase();
        }
    }

    static class Splitter extends Processor {
        String process(Object input) {
            return Arrays.toString(((String)input).split(" "));
        }
    }

    public static void process(Processor p, Object s) {
        print("Using Processor " + p.name());
        print(p.process(s));
    }

    public static String s =
            "Disagreement with beliefs is by definition incorrect";

    public static void main(String[] args) {
        process(new Upcase(), s);
        process(new Downcase(), s);
        process(new Splitter(), s);
    }
}
