package ch.hslu.ad.sw03ex;

/**
 * Binary tree that only contains unique Objects.
 */
public class SetTreeNode<T extends Comparable<T>> implements Tree<T> {

    private T data;
    private SetTreeNode<T> parent;
    private SetTreeNode<T> left;
    private SetTreeNode<T> right;

    public SetTreeNode(SetTreeNode<T> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private SetTreeNode<T> getParent() {
        return this.parent;
    }

    public void setParent(SetTreeNode<T> parent) {
        this.parent = parent;
    }

    private SetTreeNode<T> getLeft() {
        if (null == this.left) {
            this.left = new SetTreeNode<>(this);
        }
        return this.left;
    }

    public void setLeft(SetTreeNode<T> left) {
        this.left = left;
    }

    private SetTreeNode<T> getRight() {
        if (null == this.right) {
            this.right = new SetTreeNode<>(this);
        }
        return this.right;
    }

    public void setRight(SetTreeNode<T> right) {
        this.right = right;
    }

    private boolean hasData() {
        return null != this.data;
    }

    private boolean hasNoData() {
        return !this.hasData();
    }

    @Override
    public boolean add(T obj) {
        if (null == obj || obj.equals(this.getData())) {
            return false;
        }

        if (this.hasNoData()) {
            this.setData(obj);
        } else if (this.data.compareTo(obj) > 0) {
            return this.getLeft().add(obj);
        } else {
            return this.getRight().add(obj);
        }

        return true;
    }

    @Override
    public boolean search(T obj) {
        if (this.getData().equals(obj)) {
            return true;
        } else if (this.data.compareTo(obj) > 0) {
            return this.getLeft().search(obj);
        } else {
            return this.getRight().search(obj);
        }
    }

    @Override
    public String toString() {
        return "SetTreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
