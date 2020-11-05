package relative.data.structure;

/**
 * @author : chensy
 * Date : 2020/11/5 上午10:11
 */
public class NodeUtils {
    private static Node head = new Node("head");

    public static void addNode(String value) {
        //初始化要加入的节点
        Node newNode = new Node(value);

        //临时节点
        Node temp = head;

        // 找到尾节点
        while (temp.next != null) {
            temp = temp.next;
        }

        // 已经包括了头节点.next为null的情况了～
        temp.next = newNode;
    }
}
