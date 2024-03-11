package ch.hslu.ad.sw03ex;

public final class BinTreeNode<T> {
    private BinTreeNode<T> left = null;
    private BinTreeNode<T> right = null;
    private T data = null;

    public BinTreeNode() {
    }

    public BinTreeNode(T data) {
        this.setData(data);
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

    @Override
    public String toString() {
        return "BinTreeNode{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
