package ch.hslu.ad.sw03ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BinTreeNode<T extends Comparable<T>> {
    private static final Logger LOG = LoggerFactory.getLogger(BinTreeNode.class);
    private BinTreeNode<T> parent = null;
    private BinTreeNode<T> left = null;
    private BinTreeNode<T> right = null;
    private T data = null;

    public BinTreeNode(T data, BinTreeNode<T> parent, BinTreeNode<T> left, BinTreeNode<T> right) {
        this.setData(data);
        this.setParent(parent);
        this.setLeft(left);
        this.setRight(right);
    }

    public BinTreeNode(T data, BinTreeNode<T> parent) {
        this.setData(data);
    }

    public BinTreeNode() {
    }

    public BinTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(BinTreeNode<T> parent) {
        this.parent = parent;
    }

    private boolean hasLeftChild() {
        return null != this.left;
    }

    private boolean hasRightChild() {
        return null != this.right;
    }

    private boolean hasNoData() {
        return null == this.data;
    }

    public void traverseInOrder() {
        if (this.hasLeftChild()) {
            this.getLeft().traverseInOrder();
        }
        LOG.info(String.valueOf(this.data));
        if (this.hasRightChild()) {
            this.getRight().traverseInOrder();
        }
    }

    public void traversePreOrder() {
        LOG.info(String.valueOf(this.data));
        if (this.hasLeftChild()) {
            this.getLeft().traversePreOrder();
        }
        if (this.hasRightChild()) {
            this.getRight().traversePreOrder();
        }
    }

    public void traversePostOrder() {
        if (this.hasLeftChild()) {
            this.getLeft().traversePostOrder();
        }
        if (this.hasRightChild()) {
            this.getRight().traversePostOrder();
        }
        LOG.info(String.valueOf(this.data));
    }


    public void add(T data) {
        if (null == data) {
            throw new IllegalArgumentException("Element is not allowed to be null");
        }
        if (this.hasNoData()) {
            this.setData(data);
        } else if (this.getData().compareTo(data) > 0) {
            this.addLeft(data);
        } else {
            this.addRight(data);
        }
    }

    private void addLeft(T data) {
        if (this.hasLeftChild()) {
            // delegate Insertion to next Node
            this.getLeft().add(data);
        } else {
            // node does not exist, create new and add Data
            this.setLeft(new BinTreeNode<>(data, this));
        }
    }

    private void addRight(T data) {
        if (this.hasRightChild()) {
            // delegate Insertion to next Node
            this.getRight().add(data);
        } else {
            // node does not exist, create new and add Data
            this.setRight(new BinTreeNode<>(data, this));
        }
    }

    private boolean hasParent() {
        return null != this.parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinTreeNode<T> left) {
        this.left = left;
    }

    public BinTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinTreeNode<T> right) {
        this.right = right;
    }

    private String indentedToString(BinTreeNode<T> node, String indent, NodeSide nodeSide) {
        if (node == null) {
            return "";
        }
        return this.indentedToString(node.getRight(), indent + "    ", NodeSide.Right)
                + indent + nodeSide.getIndicator() + "══" + " " + node.getData() + "\n"
                + this.indentedToString(node.getLeft(), indent + "    ", NodeSide.Left);
    }

    @Override
    public String toString() {
        return indentedToString(this, "", NodeSide.Root);
    }

    private enum NodeSide {
        Left("╚"), Right("╔"), Root("");

        private final String indicator;

        NodeSide(String nsi) {
            this.indicator = nsi;
        }

        public String getIndicator() {
            return this.indicator;
        }
    }
}
