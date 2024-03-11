package ch.hslu.ad.sw03ex;

public final class BinTreeByHand {
    public static void main(String[] args) {
        var myTree = new BinTree<Integer>();

        var root = myTree.getRoot();
        root = new BinTreeNode<Integer>(10);

        root.setLeft(new BinTreeNode<>(8));
        root.setRight(new BinTreeNode<>(15));

        root.getRight().setRight(new BinTreeNode<>(17));
        root.getRight().setLeft(new BinTreeNode<>(16));

        root.getLeft().setRight(new BinTreeNode<>(9));
        root.getLeft().setLeft(new BinTreeNode<>(5));


        System.out.println(root.toString());

    }
}
