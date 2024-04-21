package thinking.collection_deep;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class CollectionDataGeneration {

    /**
     * 知识点（17.2）：随机产生字符串
     */
    public static void main(String[] args) {
        System.out.println(new ArrayList<>(
                CollectionData.list(new RandomGenerator.String(9), 10)));
        System.out.println(new HashSet<>(
                CollectionData.list(new RandomGenerator.Integer(), 10)
        ));
    }
}
