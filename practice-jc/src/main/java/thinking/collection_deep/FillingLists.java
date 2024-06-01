package thinking.collection_deep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chensy
 * @date 2024/4/21
 */
public class FillingLists { //@TkY-Doing

    /**
     * 知识点（17.2）：填充容器
     *
     * 关联点学习：
     * 1）ArrayList中的内部类Itr了解（Doing）
     *
     * 问题点答疑：
     * 1）System.out.println(obj)输出集合对象的逻辑是什么？（Done）
     *    解答：println(obj)输出对象时，会调用对象的toString()方法，由于ArrayList继承了抽象类AbstractCollection的toString方法
     *         所以会先进入AbstractCollection的toString方法，进入方法后，会依次遍历集合中的具体对象的toString方法，最后通过"[对象toString()...]"
     *         的方式，构建输出的字符串。
     */

    static class StringAddress {
        private String s;
        public StringAddress(String s) { this.s = s; }
        public String toString() {
            return super.toString() + " " + s;
        }
    }

    public static void main(String[] args) { //Doing
        List<StringAddress> list = new ArrayList<StringAddress>(
                Collections.nCopies(4, new StringAddress("Hello"))); //通过拷贝4个StringAddress，形成列表CopiesList，再构建List
        System.out.println("填充前：" + list);
        Collections.fill(list, new StringAddress("World"));
        System.out.println("填充后：" + list);

        /**
         * 输出结果：
         * 填充前：[thinking.collection_deep.FillingLists$StringAddress@232204a1 Hello, thinking.collection_deep.FillingLists$StringAddress@232204a1 Hello, thinking.collection_deep.FillingLists$StringAddress@232204a1 Hello, thinking.collection_deep.FillingLists$StringAddress@232204a1 Hello]
         * 填充后：[thinking.collection_deep.FillingLists$StringAddress@4aa298b7 World, thinking.collection_deep.FillingLists$StringAddress@4aa298b7 World, thinking.collection_deep.FillingLists$StringAddress@4aa298b7 World, thinking.collection_deep.FillingLists$StringAddress@4aa298b7 World]
         *
         * 结果分析：
         * 1）
         *
         * 问题点答疑：
         * 1）Collections.nCopies(4, new StringAddress("Hello"))是在什么进行拷贝元素的？构造方法中并没有考到拷贝？
         *    解答：在new ArrayList(...) 通过集合构建List时，会调用 elementData = c.toArray(); 而在CopiesList中的toArray()
         *         就会拷贝n个指定对象构建数组，设置到List中的数组中
         */
    }
}
