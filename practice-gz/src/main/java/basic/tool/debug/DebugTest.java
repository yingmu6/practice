package basic.tool.debug;

import org.junit.Test;

/**
 * @author chensy
 * @date 2024/4/10
 */
public class DebugTest {

    /**
     * 知识点：Debug调试技巧
     * 1）可以通过设置条件断点condition，如条件type.equals(DynamicConfigurationFactory.class)、type instanceof String
     *   （条件只要满足java语法即可）
     *
     * 参考链接：
     * a）https://juejin.cn/post/6844904190691508232  IDEA-Debug高级技巧
     */

    @Test
    public void basicUse() {
        Object[] obj = new Object[] {
                Integer.valueOf(3), String.valueOf("aa"), Double.valueOf(6.0)
        };

        for (int i = 0; i < obj.length; i++) {
            System.out.println(obj[i]); //可在此处断点，condition可以设置为 "obj instanceof String" 等，即在满足条件时候进入断点（很有用，可以过滤掉不需要的debug）
        }
    }

    @Test
    public void compareClassInfo() {
        DebugTest debugTest = new DebugTest();
        Object obj = debugTest;
        System.out.println(obj.getClass().getSimpleName());
        System.out.println(obj.getClass().getSimpleName() == "DebugTest");
        System.out.println(obj.getClass().getSimpleName().equals("DebugTest"));
        System.out.println(obj instanceof DebugTest);

        /**
         * 输出结果：
         * DebugTest
         * false
         * true
         * true
         *
         * 结果分析：
         * 1）== 比较的是引用的地址，obj.getClass().getSimpleName() 与 "DebugTest"是不同的地址，所以返回false
         *
         * 结果概括：
         */
    }

}
