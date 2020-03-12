package relative.spi.copy;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author : chensy
 * Date : 2020-03-12 14:43
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);

        // 使用Iterator或for循环
        Iterator it = shouts.iterator();
        while (it.hasNext()) {
            IShout iShout = (IShout) it.next();
            iShout.shout();
        }
//        for (IShout s : shouts) {
//            s.shout();
//        }
    }
}
