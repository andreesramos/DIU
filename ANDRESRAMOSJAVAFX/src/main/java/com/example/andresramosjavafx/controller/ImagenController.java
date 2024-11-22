package com.example.andresramosjavafx.controller;

import com.example.andresramosjavafx.vista.Moneda;
import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;

import javafx.scene.control.Label;

import java.util.List;

public class ImagenController {

    @FXML
    private ImageView imagenMoneda;

    @FXML
    private Label numMonedaLabel;

    @FXML
    private void initialize() {

    }

    public void setNumeroMonedas(Integer num) {
        numMonedaLabel.setText(num.toString());
    }
}
