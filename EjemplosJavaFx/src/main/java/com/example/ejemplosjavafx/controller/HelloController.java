package com.example.ejemplosjavafx.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class HelloController {
    private IntegerProperty numPulsaciones = new SimpleIntegerProperty();

    @FXML
    private Label lbCont;

    @FXML
    private Button btMas;

    @FXML
    private Button btMenos;

    @FXML
    private Button btCero;

    @FXML
    private ProgressBar progreso;

    @FXML
    protected void onButtonClick() {
        btMas.setOnAction(e -> operacion(btMas));
        btMenos.setOnAction(e -> operacion(btMenos));
        btCero.setOnAction(e -> operacion(btCero));
    }

    private void operacion(Button button){
        numPulsaciones.set(button == btMas ? numPulsaciones.get() + 1 :
                button == btMenos ? numPulsaciones.get() - 1 : 0);
    }
}