package ch.hslu.ad.sw03ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BinTreeByHand {

    private static final Logger LOG = LoggerFactory.getLogger(BinTreeByHand.class);

    public static void main(String[] args) {
        var myTree = new BinTreeNode<Integer>();
        myTree.add(6);
        myTree.add(5);
        myTree.add(10);
        myTree.add(4);
        myTree.add(5);
        myTree.add(10);
        myTree.add(2);
        myTree.add(4);
        myTree.add(7);
        myTree.add(6);
        myTree.add(4);

        LOG.info("Traversing Tree InOrder...");
        myTree.traverseInOrder();

        LOG.info("Printing toString()...");
        System.out.println(myTree);
    }
}
