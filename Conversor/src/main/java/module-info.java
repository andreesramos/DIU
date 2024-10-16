module com.example.conversor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires AccesoBBDDMoneda;
    requires java.sql;
    requires java.desktop;

    opens com.example.conversor to javafx.fxml;
    exports com.example.conversor;
    exports com.example.conversor.controller;
    opens com.example.conversor.controller to javafx.fxml;
}