package interview.offer_come.data_struct;

/**
 * @author chensy
 * @date 2024/3/16
 */
public class BinarySortTree {

    private Node root; //todo 待确定

    public class Node {

        private int value;

        private Node left;

        private Node right;

        public Node() {

        }

        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public Node(int value) {
            this(null, null, value);
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public void insertBST(int key) {
        Node p = root;
        Node prev = null;
        while (p != null) {
            prev = p;
            if (key < p.getValue()) {
                p = p.getLeft();
            } else if (key > p.getValue()) {
                p = p.getRight();
            } else {
                return;
            }
        }

        if (root == null) {
            root = new Node(key);
        } else if (key < prev.getValue()) {
            prev.setLeft(new Node(key));
        } else {
            prev.setRight(new Node(key));
        }
    }

    public void deleteBST(int key) {
        deleteBST(root, key);
    }

    private boolean deleteBST(Node node, int key) {
        if (node == null) {
            return false;
        } else {
            if (key == node.getValue()) {
                return delete(node);
            } else if (key < node.getValue()) {
                return deleteBST(node.getLeft(), key);
            } else {
                return deleteBST(node.getRight(), key);
            }
        }
    }

    private boolean delete(Node node) {
        Node temp = null;
        if (node.getRight() == null) {
            temp = node;
            node = node.getLeft();
        } else if (node.getLeft() == null) {
            temp = node;
            node = node.getRight();
        } else {
            temp = node;
            Node s = node;
            s = s.getLeft();
            while (s.getRight() != null) {
                temp = s;
                s = s.getRight();
            }
            node.setValue(s.getValue());
            if (temp != node) {
                temp.setRight(s.getLeft());
            } else {
                temp.setLeft(s.getLeft());
            }
        }
        return true;
    }

    public boolean searchBST(int key) {
        Node current = root;
        while (current != null) {
            if (key == current.getValue()) {
                return true;
            } else if (key < current.getValue()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return false;
    }
}
