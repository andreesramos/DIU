package com.example.ejemplosjavafx;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class ContadorPulsaciones extends Application {

    private IntegerProperty numPulsaciones = new SimpleIntegerProperty();
    private Label lbCont;
    Button btMas, btMenos, btCero;

    private void operacion(Button button/*int num*/){
        /*pulsaciones=num==0 ? pulsaciones=0 : pulsaciones+num;
        //int i=num==0 ? pulsaciones=0 : (num==1 ? pulsaciones++ : pulsaciones--);
        lbCont.setText(String.valueOf(pulsaciones));*/
        numPulsaciones.set(button == btMas ? numPulsaciones.get() + 1 :
                button == btMenos ? numPulsaciones.get() - 1 : 0);
    }

    @Override
    public void start(Stage escenarioPrincipal){
        try{
            Stage escenarioSecundario=new Stage();
            Pane raiz=new Pane();
            raiz.getStyleClass().add("raiz");


            btMas= new Button("+");
            btMas.setId("btMas");

            btMenos=new Button("-");
            btMenos.setId("btMenos");

            btCero=new Button("0");
            btCero.setId("btCero");

            lbCont = new Label("1");
            lbCont.setId("texto");
            lbCont.setFont(Font.font(30));

            numPulsaciones.addListener((obs, oldVal, newVal) -> lbCont.setText(newVal.toString()));

            btMas.setFont(Font.font(20));
            btMenos.setFont(Font.font(20));
            btCero.setFont(Font.font(20));

            btMas.setLayoutX(70);
            btMas.setLayoutY(30);
            btMenos.setLayoutX(190);
            btMenos.setLayoutY(30);
            btCero.setLayoutX(300);
            btCero.setLayoutY(30);
            lbCont.setLayoutX(200);
            lbCont.setLayoutY(110);

            btMas.setOnAction(e -> operacion(btMas));
            btMenos.setOnAction(e -> operacion(btMenos));
            btCero.setOnAction(e -> operacion(btCero));

            raiz.getChildren().addAll(btMas, btMenos, btCero, lbCont);

            Scene escena=new Scene(raiz, 400, 175);
            escena.getStylesheets().add(getClass().getResource("/estilos/contador.css").toExternalForm());
            escenarioPrincipal.setTitle("Contador de pulsaciones");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public IntegerProperty getNumPulsaciones(){
        return numPulsaciones;
    }
}
