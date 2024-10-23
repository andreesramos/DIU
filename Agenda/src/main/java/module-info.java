module com.example.agenda {
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
    requires java.sql;

    opens com.example.agenda to javafx.fxml;
    exports com.example.agenda;
    exports com.example.agenda.controller;
    opens com.example.agenda.controller to javafx.fxml;
    exports com.example.agenda.modelo;
    opens com.example.agenda.modelo to javafx.fxml;
    exports com.example.agenda.vista;
    opens com.example.agenda.vista to javafx.fxml;
    exports com.example.agenda.modelo.utilidad;
    opens com.example.agenda.modelo.utilidad to javafx.fxml;
}