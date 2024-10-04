package com.example.contador.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    private IntegerProperty numPulsaciones = new SimpleIntegerProperty();

    void setNumPulsaciones(IntegerProperty n){
        this.numPulsaciones=numPulsaciones;
    }

    IntegerProperty getNumPulsaciones(){
        return numPulsaciones;
    }

    @FXML
    private Label lbCont;

    @FXML
    private Button btMas;

    @FXML
    private Button btMenos;

    @FXML
    private Button btCero;

    @FXML
    private TextField texto;

    @FXML
    private ProgressBar progreso;

    public void initialize(){
        numPulsaciones.addListener((o, oldVal, newVal) -> lbCont.setText(newVal.toString()));
        //numPulsaciones.bind(Integer.parseInt(lbCont.getText()));
        progreso.progressProperty().bind(numPulsaciones.divide(50));
    }


   @FXML
    public void sumar(){
       numPulsaciones.set(numPulsaciones.get()+1);
        lbCont.setText(String.valueOf(numPulsaciones.getValue()));
    }

    @FXML
    public void restar(){
        numPulsaciones.set(numPulsaciones.get()-1);
        lbCont.setText(String.valueOf(numPulsaciones.getValue()));
    }

    @FXML
    public void cero(){
        numPulsaciones.set(0);
        lbCont.setText(String.valueOf(numPulsaciones.getValue()));
    }

    @FXML
    public void cambiarTexto(){
        numPulsaciones.set(Integer.parseInt(texto.getText()));
        lbCont.setText(String.valueOf(numPulsaciones.getValue()));
    }

    /*@FXML
    public void cambiarProgreso(){
        double p=(numPulsaciones.getValue()/50.0);
        progreso.setProgress(p);
    }*/
}