package interview.offer_come.data_struct;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class SingleLinkedList { //@MsY-Doing

    /**
     * 知识点：
     *
     * 知识点概括：
     */

    private int length;

    private Node head;

    public SingleLinkedList() {
        this.length = 0;
        this.head = null;
    }

    private class Node {

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
      // todo 遍历列表，从头到尾输出所有元素
      return "";
    }

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addHead("zhang san");
        linkedList.addHead("li si");
        linkedList.addHead("wang wu");

        System.out.println(linkedList);
    }
}
