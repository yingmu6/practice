package relative.data.structure;

/**
 * 链表测试 https://www.jianshu.com/p/73d56c3d228c 链表的数据结构
 * https://www.cnblogs.com/alsf/p/5520266.html  单向列表的实现
 * @author : chensy
 * Date : 2020-03-07 22:57
 */
public class LinkTest {
    public static void main(String[] args) {
        singleListV2();
    }

    // 单向列表
    private static void singleListV2() {
        Node head = new Node();
        head.setData("head");
        Node a = new Node();
        a.setData("A");
        Node b = new Node();
        b.setData("B");

        head.setNext(a);
        a.setNext(b);

        b.setNext(null); //单向链表
        //b.setNext(head); // 循环列表
        printNode(head);
    }

    // 递归调用，输出结果
    private static void printNode(Node node) {
        System.out.println(node.getData());
        if (node.getNext() != null) {
            printNode(node.getNext());
        }
    }
}
