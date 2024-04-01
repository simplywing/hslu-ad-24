package ch.hslu.ad.sw05ex.canvastests;

import ch.hslu.ad.helper.Canvas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public final class Demo {

    private static final Logger LOG = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        Canvas cvs = Canvas.initCanvas("Recursion Maze", 1000, 800, Color.black, "/icon5.png");

        Thread.startVirtualThread(new Animation(cvs));
    }

    private record Animation(Canvas canvas) implements Runnable {
        private static final Logger LOG = LoggerFactory.getLogger(Animation.class);

        private static final Object lock1 = new Object();

        @Override
        public void run() {
            int ypos = 0;
            while (ypos < canvas.getHeight()) {
                ypos += 5;
                canvas.draw(Demo.class, Color.CYAN, new Ellipse2D.Double(Math.abs(canvas.getWidth() / 2) - 75, ypos, 150, 150));
                //canvas.draw(new Object(), Color.BLUE, new Ellipse2D.Double(150, 30, 150, 150));

                canvas.wait(16);

//                synchronized (lock1) {
//                    try {
//                        this.wait(15);
//                    } catch (InterruptedException e) {
//                        LOG.error(e.getMessage());
//                    }
//                }

            }
        }
    }
}
