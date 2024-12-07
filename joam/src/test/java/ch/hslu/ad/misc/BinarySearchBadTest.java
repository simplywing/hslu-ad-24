package ch.hslu.ad.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchBadTest {

    @Test
    void testSearch() {
        int[] test = {1, 3, 5, 6, 8, 11, 12};
        var s = new BinarySearchBad(test);
        assertEquals(5, s.search(11));
    }
}