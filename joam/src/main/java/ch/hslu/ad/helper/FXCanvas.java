package ch.hslu.ad.helper;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;


public final class FXCanvas extends Application {

    public static final int width = 1280;
    public static final int height = 800;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Canvas");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(FXCanvas.class.getResourceAsStream("/icon2.png"))));

        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas();
        canvas.setWidth(FXCanvas.width);
        canvas.setHeight(FXCanvas.height);

        GraphicsContext gc2d = canvas.getGraphicsContext2D();

        VBox vbox = new VBox(canvas);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        Thread.startVirtualThread(() -> {
            for (int i = 0; i < 350; i++) {

                try {
                    Thread.sleep(1000 / 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int finalI = i;
                Platform.runLater(() -> {
                    gc2d.setFill(Color.valueOf("#ffffff"));
                    gc2d.fillRect(0, 0, FXCanvas.width, FXCanvas.height);

                    gc2d.setFill(Color.valueOf("#0000ff"));
                    gc2d.fillRect(200 + (finalI * 2), 200, 50 + (finalI * 0.25), 50 + (finalI * 0.25));

                    gc2d.setStroke(Color.valueOf("#00ff00"));
                    gc2d.strokeRect(200 + (finalI * 2), 350, 50 + (finalI * 0.25), 50 + (finalI * 0.25));

                    gc2d.setFill(Color.valueOf("#ff0000"));
                    gc2d.fillOval(200 + (finalI * 2), 500, 50 + (finalI * 0.25), 50 + (finalI * 0.25));
                });

            }
        });
    }
}
