package thinking.holder_obj;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author orange
 * @date 2024/6/5
 */
public class ForEachCollections {

    /**
     * 知识点（11.13 ）：Foreach与迭代器
     */
    public static void main(String[] args) {
        Collection<String> cs = new LinkedList<>();
        Collections.addAll(cs, "Take the long way home".split(" "));
        for (String s : cs) {
            System.out.println("'" + s + "' ");
        }
    }
}
