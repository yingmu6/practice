package thinking.interface_relative.interfaceprocessor;


import java.util.Arrays;

/**
 * @author orange
 * @date 2024/6/5
 */
public abstract class StringProcessor implements Processor{
    public String name() {
        return getClass().getSimpleName();
    }
    public abstract String process(Object input);
    public static String s =
            "If she weighs the same as a duck, she's made of wood";

    public static void main(String[] args) {
    }

    class Upcase extends StringProcessor {
        public String process(Object input) {
            return ((String) input).toUpperCase();
        }
    }

    class Downcase extends StringProcessor {
        public String process(Object input) {
            return ((String) input).toLowerCase();
        }
    }

    class Splitter extends StringProcessor {
        public String process(Object input) {
            return Arrays.toString(((String)input).split(" "));
        }
    }
}
