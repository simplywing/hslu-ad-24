package ch.hslu.ad.sw03ex;

public final class BinTreeByHand {
    public static void main(String[] args) {

        var myTree = new BinTreeNode<Integer>();
        myTree.add(6);
        myTree.add(5);
        myTree.add(10);
        myTree.add(4);
        myTree.add(7);

        myTree.traverseInOrder();
    }
}
