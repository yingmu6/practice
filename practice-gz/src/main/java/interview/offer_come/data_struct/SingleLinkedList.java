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
     * 1）
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
    }

    public Object addHead(Object obj) {
        Node newHead = new Node(obj);
        if (length != 0) {
            newHead.next = head;
        }
        head = newHead;
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

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        Node node1 = new Node("zhang san");
        Node node2 = new Node("li si");
        Node node3 = new Node("wang wu");

        linkedList.addHead(node1);
        linkedList.addHead(node2);
        linkedList.addHead(node3);

        System.out.println("------添加节点---------");
        System.out.println(linkedList);

        System.out.println("------删除节点---------");
        System.out.println(linkedList);

        System.out.println("------查找节点---------");
        System.out.println(linkedList.find(node1));
    }
}
