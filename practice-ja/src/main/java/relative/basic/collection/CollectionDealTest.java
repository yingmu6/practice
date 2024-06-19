package relative.basic.collection;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author chensy
 * @date 13/07/2022
 */
public class CollectionDealTest {
    public static void main(String[] args) {
        diff();
        retainAll();
    }

    public static void diff() { //求差集
        List<String> aList = Lists.newArrayList("11", "22", "33");

//        List<String> cList = aList; //采取赋值的方式，列表改变，关联的集合也会对应改变
        List<String> cList = Lists.newArrayList(aList); //重新产生一个list对象，新的值改变不会引起老的值改变
        List<String> bList = Lists.newArrayList("22", "44");
        cList.removeAll(bList);
        System.out.println(aList);
        System.out.println(cList);
    }

    public static void retainAll() { //求交集
        List<String> aList = Lists.newArrayList("11", "22", "33");
        List<String> bList = Lists.newArrayList("22", "44");
        aList.retainAll(bList);
        System.out.println(aList);
    }
}
