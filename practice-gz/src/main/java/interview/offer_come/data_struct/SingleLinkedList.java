package interview.offer_come.data_struct;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class SingleLinkedList { //@MsY-Doing

    /**
     * 知识点：单向列表
     *
     * 知识点概括：
     * 1）单向节点包含数据域、指向下一个节点的指针域，插入节点时，最主要的是更新指针域
     */

    private int length;

    private Node head;

    public SingleLinkedList() {
        this.length = 0;
        this.head = null;
    }

    static private class Node {

        private Object data;

        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public String toString() {
            System.out.println("Node :" + data);
            return String.valueOf(data);
        }
    }

    public Object addHead(Object obj) { //头插法（在链表头部添加节点）
        Node newHead = new Node(obj); //待插入的节点
        if (length != 0) { //处理待插入节点的指针域
            newHead.next = head; //引用赋值，即两个引用指向相同的对象，有相同的地址
        }
        head = newHead; //把待插入节点作为头节点
        length++;
        return obj;
    }

    public boolean delete(Object value) {
        if (length == 0) {
            return false;
        }

        Node current = head;
        Node previous = head;
        while(current.data != value) {
            if (current.next == null) {
                return false;
            } else {
                previous = current;
                current = current.next;
            }
        }

        if (current == head) {
            head = current.next;
            length--;
        } else {
            previous.next = current.next;
            length--;
        }
        return true;
    }

    public Node find(Object obj) {
        Node current = head;
        int tempSize = length;
        while (tempSize > 0) {
            if (obj.equals(current.data)) {
                return current;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }

    public String toString() {
        Node temp = head;
      StringBuffer dataBuf = new StringBuffer((temp != null ? temp.data : "") + "\n");
      while(temp.next != null) {
          dataBuf.append(temp.next.data + "\n");
          temp = temp.next;
      }
      return dataBuf.toString();
    }

    public static void main(String[] args) { //Doing_@pause-07/04
        SingleLinkedList linkedList = new SingleLinkedList();
//        Node node1 = new Node("zhang san");
//        Node node2 = new Node("li si");
//        Node node3 = new Node("wang wu");

        String data1 = "zhang san";
        String data2 = "li si";
        String data3 = "wang wu";
        linkedList.addHead(data1);
        linkedList.addHead(data2);
        linkedList.addHead(data3);

        System.out.println("------添加节点---------");
        System.out.println(linkedList);

        System.out.println("------删除节点---------");
        linkedList.delete(data2);
        System.out.println(linkedList);

        System.out.println("------查找节点---------");
        System.out.println(linkedList.find(data1));

        /**
         * 输出结果：
         *
         * 结果分析：
         *
         * 问题点答疑：
         * 1）感觉这个单链表，不够纯正。因为节点的数据域data，应该是具体的基本类型，而不是又是Node类型？
         */
    }
}
