package thinking.collection_deep;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class CollectionDataGeneration { //@TkY-Done

    /**
     * 知识点（17.2）：随机产生字符串
     */
    public static void main(String[] args) {
        System.out.println(new ArrayList<>(
                CollectionData.list(new RandomGenerator.String(9), 10)));
        System.out.println(new HashSet<>(
                CollectionData.list(new RandomGenerator.Integer(), 10)
        ));

        /**
         * 输出结果：
         * [YNzbrnyGc, FOWZnTcQr, GseGZMmJM, RoEsuEcUO, neOEdLsmw, HLGEahKcx, rEqUCBbkI, naMesbtWH, kjUrUkZPg, wsqPzDyCy]
         * [2017, 8037, 871, 7882, 6090, 4779, 299, 573, 4367, 3455]
         *
         * 结果分析：
         * 1）在RandomGenerator的静态内部类String中，会从预先定义的字符数组中按产生的随机值作为下标获取字符数组中值
         * 2）在RandomGenerator的静态内部类Integer中，会产生10000以内的随机数值
         */
    }
}
