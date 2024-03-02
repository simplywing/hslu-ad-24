package ch.hslu.ad.sw01ex;

import org.junit.jupiter.api.Test;

class CanvasTest {

    @Test
    void testPaintBucket() {
        var cvs = new Canvas();
        cvs.colorArea(4, 3, '░', '▓');
        
        System.out.println(String.format("Method calls: %s", cvs.getMethodCallCount()));
        System.out.println(String.format("setPixel() Method calls: %s", cvs.getSetPixelCallCount()));
    }
}