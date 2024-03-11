package ch.hslu.ad.swingtests;

import javax.swing.*;
import java.awt.*;

public class MyCanvas {

    private final JFrame frame;

    MyCanvas(String title, int width, int height) {
        this.frame = new JFrame(title);
        this.frame.add(new MyCanvasComponent());
        this.frame.setSize(width, height);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    private static class MyCanvasComponent extends JComponent {
        @Override
        public void paint(Graphics g) {
            Graphics2D graphic2d = (Graphics2D) g;

            Maze maze = new Maze(graphic2d);
            maze.colorArea(4, 3, '░', '▓');
        }
    }
}