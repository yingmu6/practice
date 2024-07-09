package interview.offer_come.data_struct;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class TwoWayLinkedList { //@MsY-Doing

    /**
     * 知识点：
     *
     * 知识点概括：
     *
     * 关联点学习：
     * 1）数形结合，分析后插法、尾插法，并画出示意图（Doing）
     */

    private Node head;

    private Node tail;

    private int length;

    private class Node {

        private Object data;

        private Node next;

        private Node prev;

        public Node(Object data) {
            this.data = data;
        }
    }

    public TwoWayLinkedList() {
        this.length = 0;
        head = null;
        tail = null;
    }

    public void addHead(Object value) { //自行添加
        Node newNode = new Node(value);
        if (length == 0) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        length++;
    }

    public void addTail(Object value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        length++;
    }

    public Node deleteHead() {
        Node temp = head;
        if (length != 0) {
            head = head.next;
            head.prev = null;
            length--;
            return temp;
        } else {
            return null;
        }
    }

    public Node deleteTail() {
        Node temp = tail;
        if (length != 0) {
            tail = tail.prev;
            tail.next = null;
            length--;
            return temp;
        } else {
            return null;
        }
    }

    public String toString() {
        Node temp = head;
        StringBuffer dataBuf = new StringBuffer((temp != null ? temp.data : "") + "\n");
        while(temp.next != null) { //从第一个节点开始依次遍历
            dataBuf.append(temp.next.data + "\n");
            temp = temp.next;
        }
        return dataBuf.toString();
    }

    public static void main(String[] args) { //Doing
        TwoWayLinkedList linkedList = new TwoWayLinkedList();

        String data1 = "zhang san";
        String data2 = "li si";
        String data3 = "wang wu";
        String data4 = "liu liu";
        String data5 = "chen xiao";
//        linkedList.addHead(data1);
//        linkedList.addHead(data2);
//        linkedList.addHead(data3);
//        linkedList.addHead(data4);
//        linkedList.addHead(data5);

        linkedList.addTail(data1);
        linkedList.addTail(data2);
        linkedList.addTail(data3);
        linkedList.addTail(data4);
        linkedList.addTail(data5);

        System.out.println("------双向链表：添加节点---------");
        System.out.println(linkedList);

        System.out.println("------双向链表：删除节点---------");
        linkedList.deleteHead();
//        linkedList.deleteTail();
        System.out.println(linkedList);

//        System.out.println("------双向链表：查找节点---------");
//        System.out.println(linkedList.find(data1));

        /**
         * 输出结果：
         *
         * 结果分析：
         * 1）
         *
         */
    }
}
