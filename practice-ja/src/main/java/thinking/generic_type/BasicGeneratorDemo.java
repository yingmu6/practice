package thinking.generic_type;

import net.mindview.util.BasicGenerator;
import net.mindview.util.Generator;

/**
 * @author chensy
 * @date 2024/5/18
 */
public class BasicGeneratorDemo {

    /**
     * 知识点（15.4.4）：通用的Generator
     */

    public static void main(String[] args) {
        Generator<CountedObject> gen =
                BasicGenerator.create(CountedObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
    }
}
