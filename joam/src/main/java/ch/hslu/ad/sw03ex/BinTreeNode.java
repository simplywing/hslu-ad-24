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
        this.set(left, NodeSide.Left);
        this.set(right, NodeSide.Right);
    }

    public BinTreeNode(T data, BinTreeNode<T> parent) {
        this.setParent(parent);
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

    private boolean hasChild(NodeSide side) {
        return switch (side) {
            case Right -> null != this.right;
            case Left -> null != this.left;
            case null, default -> throw new IllegalArgumentException("hasChild can only be checked left and right.");
        };
    }

    private boolean hasNoData() {
        return null == this.data;
    }

    public void traverseInOrder() {
        if (this.hasChild(NodeSide.Left)) {
            this.getNode(NodeSide.Left).traverseInOrder();
        }
        LOG.info(String.valueOf(this.data));
        if (this.hasChild(NodeSide.Right)) {
            this.getNode(NodeSide.Right).traverseInOrder();
        }
    }

    public void traversePreOrder() {
        LOG.info(String.valueOf(this.data));
        if (this.hasChild(NodeSide.Left)) {
            this.getNode(NodeSide.Left).traversePreOrder();
        }
        if (this.hasChild(NodeSide.Right)) {
            this.getNode(NodeSide.Right).traversePreOrder();
        }
    }

    public void traversePostOrder() {
        if (this.hasChild(NodeSide.Left)) {
            this.getNode(NodeSide.Left).traversePostOrder();
        }
        if (this.hasChild(NodeSide.Right)) {
            this.getNode(NodeSide.Right).traversePostOrder();
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
            this.addNode(data, NodeSide.Left);
        } else {
            this.addNode(data, NodeSide.Right);
        }
    }

    private void addNode(T data, NodeSide side) {
        if (this.hasChild(side)) {
            // delegate Insertion to next Node
            this.getNode(side).add(data);
        } else {
            // node does not exist, create new and add Data
            this.set(new BinTreeNode<>(data, this), side);
        }
    }

    private BinTreeNode<T> getNode(NodeSide side) {
        return switch (side) {
            case Left -> this.left;
            case Right -> this.right;
            case Root -> throw new IllegalArgumentException("Node of side 'Root' cannot be returned");
            case null -> throw new IllegalArgumentException("NodeSide cannot be null");
        };
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

    private void set(BinTreeNode<T> node, NodeSide side) {
        switch (side) {
            case Left -> this.left = node;
            case Right -> this.right = node;
        }
    }

    private String indentedToString(BinTreeNode<T> node, String indent, NodeSide nodeSide) {
        if (node == null) {
            return "";
        }
        return this.indentedToString(node.getNode(NodeSide.Right), indent + "    ", NodeSide.Right)
                + indent + nodeSide.getIndicator() + "══" + " " + node.getData() + "\n"
                + this.indentedToString(node.getNode(NodeSide.Left), indent + "    ", NodeSide.Left);
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
