package thinking.interface_relative.interfaceprocessor;

import static net.mindview.util.Print.print;

/**
 * @author orange
 * @date 2024/6/5
 */
public class Apply {
    public static void process(Processor p, Object s) {
        print("Using Processor " + p.name());
        print(p.process(s));
    }
}
