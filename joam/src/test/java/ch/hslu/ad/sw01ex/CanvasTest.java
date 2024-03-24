package ch.hslu.ad.sw01ex;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CanvasTest {

    @Test
    @Disabled
    void testPaintBucket() {
        var cvs = new Canvas();
        cvs.colorArea(4, 3, '░', '▓');

        System.out.println(String.format("colorArea() Method calls: %s", cvs.getColorAreaCallCount()));
        System.out.println(String.format("setPixel() Method calls: %s", cvs.getSetPixelCallCount()));
    }
}