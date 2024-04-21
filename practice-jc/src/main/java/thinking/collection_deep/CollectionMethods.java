package thinking.collection_deep;

import net.mindview.util.Countries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class CollectionMethods {

    /**
     * 知识点（17.3）：Collection的功能方法
     */
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.addAll(Countries.names(6));
        c.add("ten");
        c.add("eleven");
        print(c);
        Object[] array = c.toArray();
        String[] str = c.toArray(new String[0]);
        print("Collections.max(c) = " + Collections.max(c));
        print("Collection.min(c) = " + Collections.min(c));
        Collection<String> c2 = new ArrayList<>();
        c2.addAll(Countries.names(6));
        c.addAll(c2);
        print(c);
        c.remove(Countries.DATA[0][0]);
        print(c);
        c.remove(Countries.DATA[1][0]);
        print(c);
        c.removeAll(c2);
        print(c);
        c.addAll(c2);
        print(c);
        String val = Countries.DATA[3][0];
        print("c.constains(" + val + ") = " + c.contains(val));
        print("c.containsAll(c2) = " + c.containsAll(c2));
        Collection<String> c3 = ((List<String>)c).subList(3, 5);
        c2.retainAll(c3);
        print(c2);
        c2.removeAll(c3);
        print("c2.isEmpty() = " + c2.isEmpty());
        c = new ArrayList<>();
        c.addAll(Countries.names(6));
        print(c);
        c.clear();
        print("after c.clear():" + c);
    }
}
