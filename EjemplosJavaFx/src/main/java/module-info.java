module com.example.ejemplosjavafx {
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

    opens com.example.ejemplosjavafx to javafx.fxml;
    exports com.example.ejemplosjavafx;
    //exports com.example.ejemplosjavafx.controller;
    //opens com.example.ejemplosjavafx.controller to javafx.fxml;
}