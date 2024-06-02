package ch.hslu.ad.sw12ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordproblemTest {

    @Test
    void testIsWordLanguage1() {
        assertTrue(Wordproblem.isWordLanguage("0111010"));
    }

    @Test
    void testIsWordLanguage2() {
        assertFalse(Wordproblem.isWordLanguage("000"));
    }

    @Test
    void testIsWordLanguage3() {
        assertTrue(Wordproblem.isWordLanguage("0"));
    }

    @Test
    void testIsWordLanguage4() {
        assertTrue(Wordproblem.isWordLanguage("010"));
    }

    @Test
    void testIsWordLanguage5() {
        assertTrue(Wordproblem.isWordLanguage("01111101010"));
    }

    @Test
    void testIsWordLanguage6() {
        assertFalse(Wordproblem.isWordLanguage("011111010100"));
    }
}