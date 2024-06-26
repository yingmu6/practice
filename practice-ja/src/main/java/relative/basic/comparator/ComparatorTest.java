package relative.basic.comparator;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author chensy
 * @date 2023/8/24
 */
public class ComparatorTest {
    /**
     * Comparator_比较器测试
     * 1）A comparison（比较） function, which imposes（强制实行） a total ordering on some collection of objects
     *
     * 2）Compares its two arguments for order.  Returns a negative integer（负整数）, zero, or a positive integer as
     *    the first argument is less than（小于）, equal to, or greater than（大于） the second
     *
     * 3）对象实现Comparable接口或使用比较器Comparator都可以实现排序，实现Comparable接口是比较好的方式
     *    The Comparable interface is a good choice to use for defining the default ordering, or in other words, if it's the main way of comparing objects
     *    So why use a Comparator if we already have Comparable?
     *    There are several reasons why:
     *       a）Sometimes we can't modify the source code of the class whose objects we want to sort, thus making the use of Comparable impossible
     *       b）Using Comparators allows us to avoid adding additional code to our domain classes（避免添加额外代码）
     *       c）We can define multiple different comparison strategies（可定义多个比较策略）, which isn't possible when using Comparable
     *
     * 参考链接：
     * https://www.baeldung.com/java-comparator-comparable  comparator与comparable使用
     */

    /**
     * 场景1：使用Comparable接口实现排序
     */
    @Test
    public void test_order_with_comparable() {
        List<Player> footballTeam = new ArrayList<>();
        Player player1 = new Player(59, "Zhang san", 20);
        Player player2 = new Player(67, "Li si", 22);
        Player player3 = new Player(45, "Wang wu", 24);
        footballTeam.add(player1);
        footballTeam.add(player2);
        footballTeam.add(player3);

        System.out.println("Before Sorting : " + footballTeam);
        Collections.sort(footballTeam); //使用sort排序时，元素需要实现Comparable接口，不然会编译错误
        System.out.println("After Sorting : " + footballTeam);

        /**
         * 输出结果：
         * Before Sorting : [Zhang san, Li si, Wang wu]
         * After Sorting : [Wang wu, Zhang san, Li si]
         *
         * 结果分析：
         *
         */
    }

    /**
     * 场景2：使用Comparator比较器实现排序
     */
    @Test
    public void test_order_with_comparator() {
        List<Player2> player2List = new ArrayList<>();
        Player2 p1 = new Player2();
        p1.setName("zhang san");
        p1.setAge(10);

        Player2 p2 = new Player2();
        p2.setName("li si");
        p2.setAge(13);

        Player2 p3 = new Player2();
        p3.setName("wang wu");
        p3.setAge(11);

        player2List.add(p1);
        player2List.add(p2);
        player2List.add(p3);

        PlayerComparator playerComparator = new PlayerComparator();
        Collections.sort(player2List, playerComparator); //集合比较时，指定比较器

        System.out.println(JSON.toJSONString(player2List));

        /**
         * 输出结果：
         * [{"age":13,"name":"li si","ranking":0},{"age":11,"name":"wang wu","ranking":0},{"age":10,"name":"zhang san","ranking":0}]
         *
         * 结果分析：
         * 按PlayerComparator的compare比较逻辑进行排序
         */
    }

    public class PlayerComparator implements Comparator<Player2> {
        @Override
        public int compare(Player2 o1, Player2 o2) {
            return Integer.compare(o2.getAge(), o1.getAge()); //此处按降序排序
        }
    }
}
