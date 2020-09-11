/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package english.dictionary;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author RS
 */
public class AVLTree<T extends Comparable<T>> {

    private static final int ALLOWED_IMBALANCE = 1;
    private Node root;

    /* Constructor */
    public AVLTree() {
        root = null;
    }

    /* Function to check if tree is empty */
    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }


    /* Function to get height of node */
    public int height(Node<T> t) {
        if (t == null) {
            return -1;
        } else {
            return t.getHeight();
        }
    }

    /* Function to max of leftChild/right node */

 /* Function to insert data */
    public void insert(T data) {
        root = insert(root, data);
    }

    /* Function to insert data recursively */
    private Node<T> insert(Node<T> node, T data) {

        if (node == null) {
            return new Node<>(data);
        }
        int compareResult = data.compareTo(node.getData());
        if (compareResult < 0) {
            node.setLeftChild(insert(node.getLeftChild(), data));
        } else if (compareResult > 0) {
            node.setRightChild(insert(node.getRightChild(), data));
        } else
 ; // Duplicate; do nothing
        return balance(node);

    }

    private Node<T> balance(Node<T> t) {
        if (t == null) {
            return t;
        }

        if (height(t.getLeftChild()) - height(t.getRightChild()) > ALLOWED_IMBALANCE) {
            if (height(t.getLeftChild().getLeftChild()) >= height(t.getLeftChild().getRightChild())) {
                t = rotateWithLeftChild(t);
            } else {
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.getRightChild()) - height(t.getLeftChild()) > ALLOWED_IMBALANCE) {
            if (height(t.getRightChild().getRightChild()) >= height(t.getRightChild().getLeftChild())) {
                t = rotateWithRightChild(t);
            } else {
                t = doubleWithRightChild(t);
            }
        }

        t.setHeight(Math.max(height(t.getLeftChild()), height(t.getRightChild())) + 1);
        return t;
    }

    private int balanceNumber(Node node) {
        int L = node.getLeftChild().getHeight();
        int R = node.getRightChild().getHeight();
        if (L - R >= 2) {
            return -1;
        } else if (L - R <= -2) {
            return 1;
        }
        return 0;
    }

    /* Rotate binary tree node with leftChild child */
    private Node<T> rotateWithLeftChild(Node<T> k2) {
        Node<T> k1 = k2.getLeftChild();
        k2.setLeftChild(k1.getRightChild());
        k1.setRightChild(k2);
        k2.setHeight(Math.max(height(k2.getLeftChild()), height(k2.getRightChild())) + 1);
        k1.setHeight(Math.max(height(k1.getLeftChild()), k2.getHeight()) + 1);
        return k1;
    }

    /* Rotate binary tree node with rightChild child  */
    private Node<T> rotateWithRightChild(Node<T> k1) {

        Node<T> k2 = k1.getRightChild();
        k1.setRightChild(k2.getLeftChild());
        k2.setLeftChild(k1);
        k1.setHeight(Math.max(height(k1.getLeftChild()), height(k1.getRightChild())) + 1);
        k2.setHeight(Math.max(height(k2.getRightChild()), k1.getHeight()) + 1);
        return k2;
    }

    /**
     * Double rotate binary tree node: first leftChild child with its rightChild
     * child; then node k3 with new leftChild child
     */
    private Node<T> doubleWithLeftChild(Node<T> k3) {
        k3.setLeftChild(rotateWithRightChild(k3.getLeftChild()));
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first rightChild child with its leftChild
     * child; then node k4 with new rightChild child
     */
    private Node<T> doubleWithRightChild(Node<T> k4) {
        k4.setRightChild(rotateWithLeftChild(k4.getRightChild()));
        return rotateWithRightChild(k4);
    }

    /* Function to delete data */
    public void delete(T data) {
        root = delete(data, root);
    }

    /**
     * Internal method to delete from a subtree.
     *
     * @param data the item to delete.
     * @param node the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return node; // Item not found; do nothing
        }
        int compareResult = data.compareTo(node.getData());

        if (compareResult < 0) {
            node.setLeftChild(insert(node.getLeftChild(), data));
        } else if (compareResult > 0) {
            node.setRightChild(insert(node.getRightChild(), data));
        } else
 ;

        if (compareResult < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (compareResult > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else if (node.getLeftChild() != null && node.getRightChild() != null) // Two children
        {
            node.setData(findMin(node.getRightChild()));
            node.setRightChild(delete(node.getData(), node.getRightChild()));
        } else {
            node = (node.getLeftChild() != null) ? node.getLeftChild() : node.getRightChild();
        }
        return balance(node);
    }

    public T findMin(Node<T> r) {
        if (r == null) {
            return null;
        }
        while (r.getLeftChild() != null) {
            r = r.getLeftChild();
        }
        return r.getData();
    }

    /* Functions to count number of nodes */
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node<T> r) {
        if (r == null) {
            return 0;
        } else {
            int l = 1;
            l += countNodes(r.getLeftChild());
            l += countNodes(r.getRightChild());
            return l;
        }
    }

    /* Functions to search for an element */
    public boolean found(T key) {
        Node<T> n = search(key);
        if (n == null) {
            return false;
        } else {
            return true;
        }
    }

    public Node<T> search(T data) {
             // Tree is empty
        if (isEmpty()) {
            return null;
        }
        // Tree is not empty
        Node<T> temp = root;
        while (temp != null) {
            if (temp.getData().compareTo(data) == 0) {
                return temp;
            } else if (temp.getData().compareTo(data) > 0) {
                temp = temp.getLeftChild();
            } else {
                temp = temp.getRightChild();
            }
        }
        return null;
    }


    /* Function to print AVL Tree */
    public void PrintTree() {
        if(!isEmpty())
        { root.level = 0;
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            System.out.println(node);
            int level = node.level;
            Node<T> left = node.getLeftChild();
            Node<T> right = node.getRightChild();
            if (left != null) {
                left.level = level + 1;
                queue.add(left);
            }
            if (right != null) {
                right.level = level + 1;
                queue.add(right);
            }
        }
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }


}
