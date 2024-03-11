package ch.hslu.ad.swingtests;

import javax.swing.*;
import java.awt.*;

public class SwingTemplate extends JComponent {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Canvas Demo");
        frame.add(new SwingTemplate());
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.RED);
        graphic2d.fillRect(100, 100, 60, 60);

        graphic2d.setColor(Color.GREEN);
        graphic2d.fillRect(200, 100, 60, 60);

        graphic2d.setColor(Color.BLUE);
        graphic2d.fillRect(100, 200, 60, 60);
    }
}