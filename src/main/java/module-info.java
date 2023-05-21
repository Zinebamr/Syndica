module com.example.syndica {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.syndica to javafx.fxml;
    exports com.example.syndica;
}