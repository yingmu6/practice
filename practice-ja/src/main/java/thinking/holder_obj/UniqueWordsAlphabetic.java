package thinking.holder_obj;

import net.mindview.util.TextFile;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author orange
 * @date 2024/6/5
 */
public class UniqueWordsAlphabetic {

    /**
     * 知识点（11.9）：Set
     */
    public static void main(String[] args) {
        Set<String> words =
                new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        words.addAll(new TextFile("SetOperations.java", "\\w+"));
        System.out.println(words);
    }
}
