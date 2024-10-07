package com.example.contador.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    private IntegerProperty numPulsaciones = new SimpleIntegerProperty(0);

    void setNumPulsaciones(IntegerProperty n){
        this.numPulsaciones=numPulsaciones;
    }

    public IntegerProperty numProperty(){
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

        numPulsaciones.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newValue) {
                lbCont.setText(newValue.toString());
                cambiarProgreso(newValue.intValue());
            }
        });

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

    private void cambiarProgreso(int num){
        double p=((double)num/50.0);
        progreso.setProgress(p);
    }
}