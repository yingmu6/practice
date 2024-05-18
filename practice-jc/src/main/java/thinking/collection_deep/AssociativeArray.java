package thinking.collection_deep;

import static net.mindview.util.Print.print;

/**
 * @author chensy
 * @date 2024/4/18
 */
public class AssociativeArray<K, V> { //@TkY-Doing

    /**
     * 知识点：关联数组（Associative：联合的，关联的）
     *
     * 知识点概括：
     * 1）
     *
     * 问题点答疑：
     * 1）二维数组的元素是怎样排列的？为什么Object[][] pairs = new Object[6][2]
     *    中的pairs.length值为6？
     */
    private Object[][] pairs;
    private int index;

    public AssociativeArray(int length) {
        pairs = new Object[length][2];
    }

    public void put(K key, V value) {
        if (index >= pairs.length) //下标越界判断
            throw new ArrayIndexOutOfBoundsException();
        pairs[index++] = new Object[]{key, value};
    }

    public V get(K key) {
        for (int i = 0; i < index; i++) {
            if (key.equals(pairs[i][0]))
                return (V) pairs[i][1];
        }
        return null;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < index; i++) {
            result.append(pairs[i][0].toString());
            result.append(" ：");
            result.append(pairs[i][1].toString());
            if (i < index - 1)
                result.append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        AssociativeArray<String, String> map =
                new AssociativeArray<>(6);
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("extra", "object");
        try {
            map.put("extra2", "object2");
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        print(map);
        print(map.get("ocean"));

        /**
         * 输出结果：
         * Too many objects!
         * sky ：blue
         * grass ：green
         * ocean ：dancing
         * tree ：tall
         * earth ：brown
         * extra ：object
         * dancing
         *
         * 结果分析：
         */
    }
}
