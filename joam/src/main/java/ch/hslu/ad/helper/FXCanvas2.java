package ch.hslu.ad.helper;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class FXCanvas2 extends Application {

    // Set your panel size here
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 800;
    private static final String WINDOW_TITLE = "JavaFX Canvas";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        SmootherAnimation myAnimation = new SmootherAnimation();
        myAnimation.widthProperty().bind(root.widthProperty());
        myAnimation.heightProperty().bind(root.heightProperty());
        root.setCenter(myAnimation);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(FRAME_WIDTH);
        stage.setHeight(FRAME_HEIGHT);
        stage.getIcons().add(new Image(Objects.requireNonNull(FXCanvas.class.getResourceAsStream("/icon2.png"))));
        stage.setTitle(WINDOW_TITLE);

        // Get screen refresh rate and apply it to animation
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> {
            Screen screen = Screen.getScreensForRectangle(
                    stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()
            ).getFirst();
            if (screen == null)
                return;
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // /!\ Does ge.getScreenDevices really return same order as Screen.getScreens?
            GraphicsDevice d = ge.getScreenDevices()[Screen.getScreens().indexOf(screen)];
            int r = d.getDisplayMode().getRefreshRate(); // /!\ r is an int whereas screen refresh rate is often not an integer
            myAnimation.setRefreshRate(r);
            //TODO: re-assess when window is moved to other screen
        });

        stage.show();
    }
}

class SmootherAnimation extends Canvas {

    private static final double SPEED = 250; // Speed value to be applied in either direction in px/s
    private static final Point2D BALL_DIMS = new Point2D(50, 50);
    // Canvas painting colors
    private static final Paint BLACK = Paint.valueOf("black");
    private static final Paint RED = Paint.valueOf("red");
    private static final Paint GREEN = Paint.valueOf("ForestGreen");
    private static final Paint BLUE = Paint.valueOf("SteelBlue");
    long[] frameTimes = new long[120]; //length defines number of rendered frames to average over
    // Defines initial speed, stores current speed
    private Point2D speed = new Point2D(SPEED, SPEED);
    // Defines rectangle start position, stores current position
    private Point2D recPos = new Point2D(0, 50);
    //Frame rate measurement
    private long frameCount = 0;
    private BigDecimal fps = null;
    //Frame duration in nanoseconds according to screen refresh rate
    private long frameNs = 1_000_000_000L / 60; //Default to 60Hz

    public SmootherAnimation() throws IOException {

        AnimationTimer anim = new AnimationTimer() {
            private long previousFrame = 0;

            @Override
            public void handle(long now) {
                // Skip first frame but record its timing
                if (previousFrame == 0) {
                    previousFrame = now;
                    frameTimes[0] = now;
                    frameCount++;
                    return;
                }

                // If we had 2 JFX frames for 1 screen frame, save a cycle by skipping render
                if (now <= previousFrame)
                    return;

                // Measure FPS
                int frameIndex = (int) (frameCount % frameTimes.length);
                frameTimes[frameIndex] = now;
                if (frameCount > frameTimes.length) {
                    int prev = (int) ((frameCount + 1) % frameTimes.length);
                    long delta = now - frameTimes[prev];
                    double fr = 1e9 / ((double) delta / frameTimes.length);
                    fps = new BigDecimal(fr).setScale(2, RoundingMode.HALF_UP);
                }
                frameCount++;

                // Calculate remaining time until next screen frame (next multiple of frameNs)
                long rest = now % frameNs;
                long nextFrame = now;
                if (rest != 0) //Fix timing to next screen frame
                    nextFrame += frameNs - rest;

                // Animate
                updateWorld(previousFrame, nextFrame);
                previousFrame = nextFrame; //Saving last execution
                draw();
            }
        };

        // Start
        anim.start();
    }

    /**
     * Save frame interval in nanoseconds given passed refresh rate
     *
     * @param refreshRate in Hz
     */
    public void setRefreshRate(int refreshRate) {
        this.frameNs = 1_000_000_000L / refreshRate;
    }

    /**
     * Perform animation (calculate object positions)
     *
     * @param previousFrame previous animation frame execution time in ns
     * @param nextFrame     next animation frame execution time in ns
     */
    private void updateWorld(long previousFrame, long nextFrame) {
        double elapsed = (nextFrame - previousFrame) / 1e9; //Interval in seconds
        // Reverse when hitting borders
        BorderHit hit = rectHitsBorders(recPos.getX(), recPos.getY(),
                BALL_DIMS.getX(), BALL_DIMS.getY(),
                speed.getX(), speed.getY());

        switch (hit) {
            case TOP, BOTTOM -> speed = new Point2D(speed.getX(), speed.getY() * -1);
            case LEFT, RIGHT -> speed = new Point2D(speed.getX() * -1, speed.getY());
            case NO_HIT -> {
            }
        }

        // Update position according to speed
        recPos = recPos.add(speed.multiply(elapsed));
    }

    /**
     * Draw world onto canvas. Also display calculated frame rate and frame count
     */
    private void draw() {
        GraphicsContext gfx = this.getGraphicsContext2D();
        // Clear and draw border
        gfx.setStroke(BLACK);
        gfx.setLineWidth(1);
        gfx.clearRect(0, 0, getWidth(), getHeight());
        gfx.strokeRect(0, 0, getWidth(), getHeight());
        // Draw moving shape
        gfx.setFill(RED);
        gfx.fillOval(recPos.getX(), recPos.getY(), BALL_DIMS.getX(), BALL_DIMS.getY());
        // Draw FPS meter
        String fpsText = fps == null ? "FPS" : fps.toString();
        gfx.setTextAlign(TextAlignment.RIGHT);
        gfx.setTextBaseline(VPos.TOP);
        gfx.setFill(BLACK);
        gfx.setFont(Font.font("JetBrains Mono"));
        gfx.fillText(fpsText, getWidth() - 5, 5);
        // Draw frame counter
        gfx.setTextAlign(TextAlignment.LEFT);
        gfx.setFill(BLACK);
        gfx.fillText("" + frameCount, 5, 5);
    }

    /**
     * Tells whether moving rectangle is hitting canvas borders
     *
     * @param x      considered rectangle horizontal coordinate (top-left from left)
     * @param y      considered rectangle vertical coordinate (top-left from top)
     * @param width  considered rectangle width
     * @param height considered rectangle height
     * @param speedX speed component in x direction
     * @param speedY speed component in y direction
     * @return BorderHit with the information where and if the border was hit
     */
    private BorderHit rectHitsBorders(double x, double y, double width, double height, double speedX, double speedY) {
        Rectangle2D frame = new Rectangle2D(0, 0, getWidth(), getHeight());
        Rectangle2D rect = new Rectangle2D(x, y, width, height);
        if (speedX < 0 && rect.getMinX() < frame.getMinX())
            return BorderHit.LEFT;
        else if (speedX > 0 && rect.getMaxX() > frame.getMaxX())
            return BorderHit.RIGHT;
        else if (speedY < 0 && rect.getMinY() < frame.getMinY())
            return BorderHit.TOP;
        else if (speedY > 0 && rect.getMaxY() > frame.getMaxY())
            return BorderHit.BOTTOM;
        return BorderHit.NO_HIT;
    }

    private enum BorderHit {
        TOP, BOTTOM, LEFT, RIGHT, NO_HIT
    }

}