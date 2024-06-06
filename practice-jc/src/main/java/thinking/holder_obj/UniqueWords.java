package thinking.holder_obj;

import net.mindview.util.TextFile;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author orange
 * @date 2024/6/5
 */
public class UniqueWords {

    /**
     * 知识点（11.9）：Set
     */
    public static void main(String[] args) {
        Set<String> words = new TreeSet<>(
                new TextFile("SetOperation.java", "\\w+"));
        System.out.println(words);
    }
}
