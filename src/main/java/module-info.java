module com.compression.compression_svd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;
    requires javafx.swing;

    opens com.compression.compression_svd to javafx.fxml;
    exports com.compression.compression_svd;
}