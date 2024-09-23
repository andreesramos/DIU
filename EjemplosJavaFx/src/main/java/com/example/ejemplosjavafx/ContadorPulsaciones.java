package com.example.ejemplosjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class ContadorPulsaciones extends Application {

    @Override
    public void start(Stage escenarioPrincipal){
        try{
            Pane raiz=new Pane();

            Button btMas, btMenos, btCero;
            btMas= new Button();
            btMenos=new Button();
            btCero=new Button();
            btMas.setText("+");
            btMenos.setText("-");
            btCero.setText("0");

            Label lbCont = new Label("1");
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

            raiz.getChildren().addAll(btMas, btMenos, btCero, lbCont);

            Scene escena=new Scene(raiz, 400, 175);
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
