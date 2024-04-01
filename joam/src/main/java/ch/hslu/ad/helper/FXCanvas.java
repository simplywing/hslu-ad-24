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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Canvas");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(FXCanvas.class.getResourceAsStream("/icon7.png"))));

        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas();
        canvas.setWidth(1280);
        canvas.setHeight(800);

        GraphicsContext gc2d = canvas.getGraphicsContext2D();

        gc2d.setStroke(Color.valueOf("#0000ff"));
        gc2d.strokeRect(200, 200, 200, 200);

        VBox vbox = new VBox(canvas);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        Thread.startVirtualThread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    gc2d.setStroke(Color.valueOf("#0000ff"));
                    gc2d.strokeRect(200, 400, 200, 200);
                });
            }
        });
    }
}
