package com.csy.structure.linked.ext;

/**
 * @author chensy
 * @date 2023/8/27
 */
public class MyLinkedList<E> {

    // 声明头节点
    private Node<E> head;
    // 声明尾节点
    private Node<E> last;
    // 链表的大小
    private int size;

    public MyLinkedList() {
        head = new Node<E>();// 实例化头节点（创建head节点对象）
        last = head; //引用赋值，则last、head引用指向相同的对象（区分出引用和对象）
    }

    /**
     * 获取单链表中存储的元素总数
     *
     * @return 返回size属性
     */
    public int size() {
        return size;
    }

    /**
     * 获取指定索引位置的节点对象
     *
     * @param index 索引位置
     * @return 返回获取到的节点对象
     */
    private Node<E> Select(int index) {
        Node<E> node = head.getNext();// 将头节点的下一个节点赋给node
        for (int i = 0; i < index; i++) { //遍历链表，获取指定位置的节点
            node = node.getNext();// 获取node的下一个节点
        }
        return node;
    }

    /**
     * 找到指定节点的数据域，并返回
     *
     * @param index
     *            索引
     * @return 节点的数据域
     */
    public E GetE(int index) {
        if (index < 0 || index > size - 1)
            return null;
        Node<E> node = Select(index); // 查找指定索引位置的节点对象
        return node.getE();// 获取节点中的数据域元素并返回
    }

    /**
     * 增加元素
     *
     * @param e 要添加的元素
     */
    public void add(E e) { //尾插法：插入元素
        Node<E> node = new Node<>(e);// 以e实例化一个节点
        last.setNext(node);// 往尾节点后加节点
        last = node;// 该节点设为最后一个节点
        size ++;
    }

    /**
     * 删除指定的节点e,并返回e
     *
     * @param index 为索引号
     * @return 返回删除的元素e
     */
    public E Delete(int index) {

        if (index < 0 || index > size)
            return null;
        if (index == 0) {// 当索引为1时，令头节点的下一个节点为头节点
            Node<E> node2 = head.getNext();
            head.setNext(node2.getNext());
            size--;
            return node2.getE();
        }
        // 获取要删除节点的前一个节点
        Node<E> bNode = Select(index - 1);
        // 获取要删除的节点
        Node<E> Node = bNode.getNext();
        // 获取要删除节点的后一个节点
        Node<E> nNode = Node.getNext();

        // 先建立删除节点的前一个节点和删除节点的后一个节点的关系
        bNode.setNext(nNode);
        // 清除dNode的下一个节点
        Node.setNext(null);
        size--;// 计数器减1
        return Node.getE();// 返回删除节点中的数据域
    }

    /**
     * 修改指点位置的数据域
     * @param x 新内容
     * @param index 索引位置
     * @return 返回修改后的数据
     */
    public E Renew(E x, int index) {
        if (index < 0 || index > size || size == 0)
            return null;
        Node<E> xnode = new Node<E>(x);// 获取一个新节点
        Node<E> node = Select(index);
        node.setE(xnode.getE());
        return node.getE();
    }
}
