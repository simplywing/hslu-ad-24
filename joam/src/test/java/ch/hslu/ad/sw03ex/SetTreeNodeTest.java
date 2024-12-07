package ch.hslu.ad.sw03ex;

import ch.hslu.demo.Point;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetTreeNodeTest {

    @Test
    void testAddToTree() {
        var tree = new SetTreeNode<Point>(null);
        assertTrue(tree.add(new Point(12, 32)));
        assertTrue(tree.add(new Point(45, 2)));
        assertTrue(tree.add(new Point(23, 3)));
        assertFalse(tree.add(new Point(23, 3)));
    }
}