package com.example.ejemplosjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class ContadorPulsaciones extends Application {

    private Label lbCont;
    private int pulsaciones=1;

    private void sumar(){
        pulsaciones++;
        lbCont.setText(String.valueOf(pulsaciones));
    }

    private void restar(){
        pulsaciones--;
        lbCont.setText((String.valueOf(pulsaciones)));
    }

    private void reiniciar(){
        pulsaciones=0;
        lbCont.setText((String.valueOf(pulsaciones)));
    }

    @Override
    public void start(Stage escenarioPrincipal){
        try{
            Pane raiz=new Pane();
            raiz.getStyleClass().add("raiz");

            Button btMas, btMenos, btCero;
            btMas= new Button("+");
            btMas.setId("btMas");

            btMenos=new Button("-");
            btMenos.setId("btMenos");

            btCero=new Button("0");
            btCero.setId("btCero");

            lbCont = new Label("1");
            lbCont.setId("texto");
            lbCont.setFont(Font.font(30));

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

            btMas.setOnAction(e -> sumar());
            btMenos.setOnAction(e -> restar());
            btCero.setOnAction(e -> reiniciar());

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

    public static void main(String[] args){
        launch(args);
    }
}
