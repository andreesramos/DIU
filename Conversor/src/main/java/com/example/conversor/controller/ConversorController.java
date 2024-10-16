package com.example.conversor.controller;

import javafx.fxml.FXML;

import javafx.scene.control.*;

public class ConversorController {

    @FXML
    private TextField euros;

    @FXML
    private TextField dolares;

    @FXML
    public float texto(){
        return Float.valueOf(euros.getText());
    }
}
