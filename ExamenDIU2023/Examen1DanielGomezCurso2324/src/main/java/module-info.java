module com.example.examen1danielgomezcurso2324 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires Modelo;
    requires java.sql;

    opens com.example.examen1danielgomezcurso2324 to javafx.fxml;
    exports com.example.examen1danielgomezcurso2324;
    exports com.example.examen1danielgomezcurso2324.controller;
    opens com.example.examen1danielgomezcurso2324.controller;
}