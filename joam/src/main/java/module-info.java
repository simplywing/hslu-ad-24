module ch.hslu.ad.helper {
    requires javafx.fxml;
    requires java.desktop;
    requires org.slf4j;
    requires javafx.graphics;
    requires org.jetbrains.annotations;

    opens ch.hslu.ad.helper to javafx.fxml;
    exports ch.hslu.ad.helper;
}
