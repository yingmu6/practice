package thinking.collection_deep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class FillingLists {

    /**
     * 知识点（17.2）：填充容器
     */

    static class StringAddress {
        private String s;
        public StringAddress(String s) { this.s = s; }
        public String toString() {
            return super.toString() + " " + s;
        }
    }

    public static void main(String[] args) {
        List<StringAddress> list = new ArrayList<StringAddress>(
                Collections.nCopies(4, new StringAddress("Hello")));
        System.out.println(list);
        Collections.fill(list, new StringAddress("World"));
        System.out.println(list);
    }
}
