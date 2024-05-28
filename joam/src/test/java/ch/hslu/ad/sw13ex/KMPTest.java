package ch.hslu.ad.sw13ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KMPTest {

    @Test
    void searchTest1() {
        int res = KMP.search("eisbein", "das ist nur ein Test");
        assertEquals(-1, res);
    }

    @Test
    void searchTest2() {
        int res = KMP.search("Test", "das ist nur ein Test");
        assertEquals(16, res);
    }

    @Test
    void searchTest3() {
        int res = KMP.search("eisbein", "eisbeissseisbeisbein");
        assertEquals(13, res);
    }

    @Test
    void searchTest4() {
        int res = KMP.search("ananas", "ananannnaasananananassssss");
        assertEquals(15, res);
    }

    @Test
    void searchTest5() {
        int res = KMP.search("ananas", "ananananas");
        assertEquals(4, res);
    }

    @Test
    void searchTest6() {
        int res = KMP.search("101011", "100111101011011");
        assertEquals(6, res);
    }

    @Test
    void searchTest7() {
        int res = KMP.search("xxxxxxx", "xxxxxxKxxxxxxx");
        assertEquals(7, res);
    }

    @Test
    void searchTest8() {
        int res = KMP.search("xxxxxxx", "xxxxxxKxxxxxxx");
        assertEquals(7, res);
    }

    @Test
    void searchTest9() {
        int res = KMP.search("oy", "oxoxoxoxoxoxoxoxoxoxoxoy");
        assertEquals(22, res);
    }

}