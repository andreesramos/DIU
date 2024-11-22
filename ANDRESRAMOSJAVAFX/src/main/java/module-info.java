module com.example.andresramosjavafx {
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
    requires AccesoBBDDExamenV2;
    requires java.desktop;

    opens com.example.andresramosjavafx to javafx.fxml;
    exports com.example.andresramosjavafx;
    exports com.example.andresramosjavafx.controller;
    opens com.example.andresramosjavafx.controller to javafx.fxml;
}