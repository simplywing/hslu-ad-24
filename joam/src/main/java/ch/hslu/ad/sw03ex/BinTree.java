package ch.hslu.ad.sw03ex;

import java.util.NoSuchElementException;

public final class BinTree<T extends Comparable<T>> {

    private BinTreeNode<T> root = null;

    BinTree() {
    }

    public BinTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinTreeNode<T> root) {
        this.root = root;
    }

    public T search(T obj) {
        if (null == this.root) {
            throw new NoSuchElementException("The tree is empty, unable to search");
        }
        return searchNode(obj, this.root).getData();
    }

    // Not finished yet:
    private BinTreeNode<T> searchNode(T needle, BinTreeNode<T> node) {
        if (null == node || null == needle) {
            return null;
        }
        if (node.getData().equals(needle)) {
            return node;
        }
        if (node.getData().compareTo(needle) > 0) {
            return searchNode(needle, node.getLeft());
        } else {
            return searchNode(needle, node.getRight());
        }
    }


}
