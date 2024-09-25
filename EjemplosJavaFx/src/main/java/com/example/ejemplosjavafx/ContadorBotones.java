package com.example.ejemplosjavafx;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ContadorBotones{

    private IntegerProperty numPulsaciones;
    private Label lbPulsaciones;
    private Button btMas, mtMenos, btCero;
    private int pulsaciones=1;

    public void setNumPulsaciones(IntegerProperty numPulsaciones){
        this.numPulsaciones=numPulsaciones;
    }

    private void operacion(int num){
        pulsaciones=num==0 ? pulsaciones=0 : pulsaciones+num;
        //int i=num==0 ? pulsaciones=0 : (num==1 ? pulsaciones++ : pulsaciones--);
        lbPulsaciones.setText(String.valueOf(pulsaciones));
    }

    public void crearEscena(Stage escenario){
        Pane raiz=new Pane();
        raiz.getStyleClass().add("raiz");

        Button btMas, btMenos, btCero;
        btMas= new Button("+");
        btMas.setId("btMas");

        btMenos=new Button("-");
        btMenos.setId("btMenos");

        btCero=new Button("0");
        btCero.setId("btCero");

        lbPulsaciones = new Label("1");
        lbPulsaciones.setId("texto");
        lbPulsaciones.setFont(Font.font(30));

        btMas.setFont(Font.font(20));
        btMenos.setFont(Font.font(20));
        btCero.setFont(Font.font(20));

        btMas.setLayoutX(70);
        btMas.setLayoutY(30);
        btMenos.setLayoutX(190);
        btMenos.setLayoutY(30);
        btCero.setLayoutX(300);
        btCero.setLayoutY(30);
        lbPulsaciones.setLayoutX(200);
        lbPulsaciones.setLayoutY(110);

        btMas.setOnAction(e -> operacion(1));
        btMenos.setOnAction(e -> operacion(-1));
        btCero.setOnAction(e -> operacion(0));

        raiz.getChildren().addAll(btMas, btMenos, btCero, lbPulsaciones);

        Scene escena=new Scene(raiz, 400, 175);
        escena.getStylesheets().add(getClass().getResource("/estilos/contador.css").toExternalForm());
        escenario.setTitle("Contador de pulsaciones");
        escenario.setScene(escena);
        escenario.show();
    }
}
