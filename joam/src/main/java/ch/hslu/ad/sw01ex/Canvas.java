package ch.hslu.ad.sw01ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Canvas {

    private static final Logger LOG = LoggerFactory.getLogger(Canvas.class);
    final char activePixelMarker = '▒';
    private final int width = 11;
    private final int height = 10;
    private final int sleepDurationMs = 600;
    private final char[][] state = new char[this.height][this.width];
    private int colorAreaCallCount = 0;
    private int setPixelCallCount = 0;
    private CanvasIndex activePixel;

    Canvas() {
        String initialState = """
                ▓▓▓▓▓▓▓▓▓▓▓
                ▓       ▓▓▓
                ▓ ▓▓▓▓▓ ▓▓▓
                ▓ ▓▓    ▓▓▓
                ▓ ▓▓ ▓▓▓▓▓▓
                ▓ ▓▓      ▓
                ▓         ▓
                ▓     ▓▓  ▓
                ▓   ▓▓▓▓▓▓▓
                ▓▓▓▓▓▓▓▓▓▓▓
                """;

        loadInitialState(initialState);
    }

    public int getSetPixelCallCount() {
        return setPixelCallCount;
    }

    public int getColorAreaCallCount() {
        return colorAreaCallCount;
    }

    private void loadInitialState(String initialState) {
        String input = initialState.replace("\n", "").replace("\r", "");

        if (input.length() != this.height * this.width) {
            throw new IllegalArgumentException("Initial State has the wrong size. Must be exactly width * height.");
        }

        int charIndex = 0;
        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) {
                this.state[j][i] = input.charAt(charIndex);
                charIndex++;
            }
        }
    }

    public void printState() {
        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) {
                if ((null != this.activePixel) && (i == this.activePixel.x()) && (j == this.activePixel.y())) {
                    System.out.print(this.activePixelMarker);
                    System.out.print(this.activePixelMarker);
                } else {
                    System.out.print(this.state[j][i]);
                    System.out.print(this.state[j][i]);
                }

            }
            System.out.println();
        }
    }

    public void printAnimationFrame() {
        this.printState();
        this.sleep();
    }

    private CanvasIndex translateCoordinates(final int x, final int y) {
        return new CanvasIndex(x - 1, this.height - y);
    }

    public void colorArea(final int x,
                          final int y,
                          final char fillChar,
                          final char borderChar) {

        this.colorAreaCallCount++;
        this.activatePixel(x, y);

        char actualChar = getPixel(x, y);
        if ((actualChar != borderChar) && (actualChar != fillChar)) {
            setPixel(x, y, fillChar);
            // deactivate and reactivate pixel for blink-effect when pixel was set
            this.deactivatePixel();
            this.activatePixel(x, y);

            colorArea(x + 1, y, fillChar, borderChar);
            this.activatePixel(x, y);

            colorArea(x, y + 1, fillChar, borderChar);
            this.activatePixel(x, y);

            colorArea(x - 1, y, fillChar, borderChar);
            this.activatePixel(x, y);

            colorArea(x, y - 1, fillChar, borderChar);
            this.activatePixel(x, y);
        }
    }

    private void activatePixel(final int x, final int y) {
        this.activePixel = translateCoordinates(x, y);
        this.printAnimationFrame();
    }

    private void deactivatePixel() {
        this.activePixel = null;
        this.printAnimationFrame();
    }

    public void setPixel(final int x, final int y, final char value) {
        this.setPixelCallCount++;
        var index = translateCoordinates(x, y);
        this.state[index.y()][index.x()] = value;
    }

    public char getPixel(final int x, final int y) {
        var index = translateCoordinates(x, y);
        return this.state[index.y()][index.x()];
    }

    private void sleep() {
        try {
            Thread.sleep(this.sleepDurationMs);
        } catch (InterruptedException e) {
            LOG.warn(e.getMessage());
        }
    }

    private record CanvasIndex(int x, int y) {
    }
}
