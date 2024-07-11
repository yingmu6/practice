package thinking.control_flow;

import org.junit.Test;

/**
 * @author orange
 * @date 2024/7/11
 */
public class ForEachCollection {

    /**
     * 知识点：forEach遍历数组和集合（新增的场景）
     *
     * 知识点概括：
     * 1）for循环的语法为：
     *       for (initialization; condition; increment/decrement) {
     *           // code to be executed
     *       }
     *
     * 2）forEach遍历数组语法为：
     *      for (dataType variableName : arrayName) {
     *          // code to be executed
     *      }
     *
     * 参考链接：
     * 1）https://www.baeldung.com/foreach-java   Guide to the Java 8 forEach
     */

    /**
     * 场景1：forEach遍历数组
     */
    @Test
    public void loopArray() {
        int[] array = {1, 3, 5, 6};
        for (int a : array) {
            System.out.print(a + " ");
        }

        /**
         * 输出结果：
         * 1 3 5 6
         *
         * 结果分析：
         * 1）
         */
    }


}
