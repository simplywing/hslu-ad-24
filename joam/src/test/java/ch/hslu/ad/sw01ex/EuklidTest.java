package ch.hslu.ad.sw01ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EuklidTest {

    private int param1 = 16;
    private int param2 = 24;
    @Test
    void testGgtIterativ1() {
        Euklid.ggtIterativ1(this.param1, this.param2);
    }

    @Test
    void testGgtIterativ2(){
        Euklid.ggtIterativ2(this.param1, this.param2);
    }

    @Test
    void testGgtRekursiv() {
        Euklid.ggtRekursiv(this.param1, this.param2);
    }
}