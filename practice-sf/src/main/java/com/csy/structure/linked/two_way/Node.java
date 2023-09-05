package com.csy.structure.linked.two_way;

 /**
 * @author chensy
 * @date 2023/9/5
 */
public class Node <E> {

    Node<E> previous;

    E element;

    Node<E> next;

    public Node() {}

    public Node(Node<E> previous, E element, Node<E> next) {
        this.previous = previous;
        this.element = element;
        this.next = next;
    }
}
