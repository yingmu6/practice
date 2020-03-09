package relative.data.structure;

/**
 * 节点
 * @author : chensy
 * Date : 2020-03-07 22:58
 */
public class Node {
    private String data;
    private Node next;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
