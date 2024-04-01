/*
 * Copyright 2024 Hochschule Luzern - Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.helper;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas. This is
 * a modification of the general purpose Canvas, specially made for the BlueJ
 * "shapes" example.
 *
 * @author Bruce Quig and Michael Kölling (mik)
 * @version 2016.02.29
 */
public final class Canvas {
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static final Object lock1 = new Object();
    private static Canvas canvasSingleton;
    //  ----- instance part -----
    private final JFrame frame;
    private final CanvasPane canvas;
    private final Color backgroundColor;

    private final String iconFile = "/icon2.png";
    private final List<Object> objects;
    private final HashMap<Object, ShapeDescription> shapes;
    private Graphics2D graphic;
    private Image canvasImage;

    /**
     * Create a Canvas.
     *
     * @param title   title to appear in Canvas Frame.
     * @param width   the desired width for the canvas.
     * @param height  the desired height for the canvas.
     * @param bgColor the desired background color of the canvas.
     */
    private Canvas(String title, int width, int height, Color bgColor, String iconFile) {
        frame = new JFrame();
        canvas = new CanvasPane();

        //Add Icon
        ImageIcon frameIcon = this.getIcon(iconFile);
        frame.setIconImage(frameIcon.getImage());
        frame.setContentPane(canvas);
        frame.setTitle(title);
        frame.setResizable(false);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor = bgColor;
        frame.pack();
        // If the component is null, or the GraphicsConfiguration associated with this component is null, the window is placed in the center of the screen.
        frame.setLocationRelativeTo(null);
        objects = new ArrayList<>();
        shapes = new HashMap<>();
    }

    /**
     * Factory method to initialize the Canvas singleton Object.
     * Must be called before using getCanvas();
     */
    public static Canvas initCanvas(final String title, final int width, final int height, final Color color, final String iconFile) {
        synchronized (Canvas.lock1) {
            if (canvasSingleton == null) {
                canvasSingleton = new Canvas(title, width, height, color, iconFile);
            } else {
                throw new IllegalStateException("Canvas can only be initialized once.");
            }
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    /**
     * Factory method to get the canvas singleton object.
     *
     * @return singleton Canvas object.
     */
    public static Canvas getCanvas() {
        if (canvasSingleton == null) {
            throw new IllegalStateException("Canvas must be initialized first. Use initCanvas to initialize.");
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    /**
     * Return the canvas width.
     *
     * @return canvas width.
     */
    public int getWidth() {
        return canvas.getWidth();
    }

    /**
     * Return the canvas height.
     *
     * @return canvas height.
     */
    public int getHeight() {
        return canvas.getHeight();
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen when
     * made visible. This method can also be used to bring an already visible
     * canvas to the front of other windows.
     *
     * @param visible boolean value representing the desired visibility of the
     *                canvas (true or false).
     */
    public void setVisible(boolean visible) {
        if (graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D) canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     *
     * @param referenceObject an object to define identity for this shape.
     * @param color           the color of the shape.
     * @param shape           the shape object to be drawn on the canvas.
     */
    // Note: this is a slightly backwards way of maintaining the shape
    // objects. It is carefully designed to keep the visible shape interfaces
    // in this project clean and simple for educational purposes.
    public synchronized void draw(Object referenceObject, Color color, Shape shape) {
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);      // add at the end
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }

    /**
     * Erase a given shape's from the screen.
     *
     * @param referenceObject the shape object to be erased.
     */
    public synchronized void erase(Object referenceObject) {
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }

    /**
     * Set the foreground color of the Canvas.
     *
     * @param color the new color for the foreground of the Canvas.
     */
    public void setForegroundColor(Color color) {
        graphic.setColor(color);
    }

    /**
     * Wait for a specified number of milliseconds before finishing. This
     * provides an easy way to specify a small delay which can be used when
     * producing animations.
     *
     * @param milliseconds the number.
     */
    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // ignoring exception at the moment
        }
    }

    /**
     * Redraw all shapes currently on the Canvas.
     */
    private void redraw() {
        erase();
        for (Object shape : objects) {
            shapes.get(shape).draw(graphic);
        }
        canvas.repaint();
    }

    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase() {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }

    private ImageIcon getIcon(final String iconFileName) {
        URL resource;

        resource = Canvas.class.getResource(iconFileName);
        if (null == resource) {
            resource = Canvas.class.getResource("/" + iconFileName);
        }

        return new ImageIcon(Objects.requireNonNull(resource));
    }

    /**
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        public void paint(Graphics g) {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }

    /**
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription {

        private final Shape shape;
        private final Color color;

        public ShapeDescription(Shape shape, Color color) {
            this.shape = shape;
            this.color = color;
        }

        public void draw(Graphics2D graphic) {
            setForegroundColor(color);
            graphic.fill(shape);
        }
    }

}
