module ch.hslu.ad.helper {
    requires org.slf4j;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.controls;

    opens ch.hslu.ad.helper to javafx.fxml;
    exports ch.hslu.ad.helper;
}