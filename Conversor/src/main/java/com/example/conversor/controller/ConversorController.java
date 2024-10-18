package com.example.conversor.controller;

import Modelo.ExcepcionMoneda;
import com.example.conversor.modelo.ConversorModelo;
import javafx.fxml.FXML;

import javafx.scene.control.*;

public class ConversorController {

    private ConversorModelo conversorModelo;

    public void setConversorModelo(ConversorModelo conversorModelo) {
        this.conversorModelo = conversorModelo;
    }

    @FXML
    private TextField euros;

    @FXML
    private TextField dolares;

    @FXML
    public float obtenerEuros(){
        return Float.valueOf(euros.getText());
    }

    public void convertirMoneda()throws ExcepcionMoneda{
        float dolar=conversorModelo.obtenerDolar(obtenerEuros());
        dolares.setText(String.valueOf(dolar));
    }

}
