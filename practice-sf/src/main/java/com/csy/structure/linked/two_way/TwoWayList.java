package com.csy.structure.linked.two_way;

/**
 * @author chensy
 * @date 2023/9/5
 */
public class TwoWayList <E> {

    private Node<E> first;

    private Node<E> last;

    private int size;

    public void add(E e) {
        Node<E> n = new Node<>(null, e, null);
        if (first == null) {
            first = n;
            last = n;
        } else {
            n.previous = last;
            last.next = n;
            last = n;
        }
        size ++;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        Node<E> temp = find(index);
        return temp == null ? null : temp.element;
    }

    private Node<E> find(int index) {
        Node<E> temp = null;
        if (first == null) {
            temp = first;
            for (int i = 0; i < index && temp != null; i++) {
                temp = temp.next;
            }
        }

        if (temp == null) {
            System.out.println("Invalid index");
        }
        return temp;
    }

    public void remove(int index) {
        Node<E> temp = find(index);
        if (temp != null) {
            Node<E> pre = temp.previous;
            Node<E> next = temp.next;

            if (pre == null) {
                first = next;
            } else {
                pre.next = next;
            }

            if (next == null) {
                last = pre;
            } else {
                next.previous = pre;
            }
            size ++;
        }
    }

    public void add(int index, E e) {
        Node<E> temp = find(index);
        Node<E> newNode = new Node<E>();
        newNode.element = e;
        if (temp != null) {
            Node<E> pre = temp.previous;
            pre.next = newNode;
            newNode.previous = pre;

            newNode = temp;
            temp.previous = newNode;
            size ++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> p = first;
        if (p != null) {
            sb.append(p.element);
            p = p.next;
        }

        while (p != null) {
            sb.append("," + p.element);
            p = p.next;
        }

        sb.append("]");
        return sb.toString();
    }

}
