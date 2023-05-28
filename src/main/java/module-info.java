module com.example.syndica {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires itextpdf;
    requires java.desktop;

    opens com.FSTM.syndica to javafx.fxml;
    opens com.FSTM.syndica.Model to javafx.base;
    exports com.FSTM.syndica;
    exports com.FSTM.syndica.controller;
    opens com.FSTM.syndica.controller to javafx.fxml;
}