module com.example.ejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires Modelo;

    opens com.example.ejemplo to javafx.fxml;
    exports com.example.ejemplo;
    exports com.example.ejemplo.controller;
    opens com.example.ejemplo.controller to javafx.fxml;
    exports com.example.ejemplo.util;
    opens com.example.ejemplo.util to javafx.fxml;
}