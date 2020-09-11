package english.dictionary;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    private Node<T> leftChild, rightChild;
    private T data;
    private int height;
    public int level;


    /* Constructor */
    public Node(T data) {
        this(data,null,null);
    }

    /* Constructor */
    public Node(T data, Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.data = data;
        if (leftChild == null && rightChild == null) {
            height = 1;
        } else if (leftChild == null) {
            height = rightChild.getHeight() + 1;
        } else if (rightChild == null) {
            height = leftChild.getHeight() + 1;
        } else {
            height = Math.max(leftChild.getHeight(), rightChild.getHeight()) + 1;
        }
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int compareTo(Node<T> n) {
        return this.data.compareTo(n.data);
    }

    @Override
    public String toString() {
        if (this.getLeftChild() != null && this.getRightChild() != null) {
            return "Level " + level + ": " + data + " || left " + this.getLeftChild().data + " | right " + this.getRightChild().data;
        } else if (this.getLeftChild() != null && this.getRightChild() == null) {
            return "Level " + level + ": " + data + " || left " + this.getLeftChild().data;
        } else if (this.getLeftChild() == null && this.getRightChild() != null) {
            return "Level " + level + ": " + data + " || right " + this.getRightChild().data;
        }

        return "Level " + level + ": " + data;
    }

}
