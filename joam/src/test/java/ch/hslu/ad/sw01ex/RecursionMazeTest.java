package ch.hslu.ad.sw01ex;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RecursionMazeTest {

    @Test
    @Disabled
    void testPaintBucket() {
        var cvs = new RecursionMaze();
        cvs.colorArea(4, 3, '░', '▓');

        System.out.printf("colorArea() Method calls: %s%n", cvs.getColorAreaCallCount());
        System.out.printf("setPixel() Method calls: %s%n", cvs.getSetPixelCallCount());
    }
}