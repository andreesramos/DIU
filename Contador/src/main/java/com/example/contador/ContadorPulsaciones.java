package com.example.contador;

import com.example.contador.controller.HelloController;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContadorPulsaciones extends Application {

    IntegerProperty numPulsaciones=new SimpleIntegerProperty(0);
    @Override
    public void start(Stage escenarioPrincipal){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ContadorPulsaciones.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 440, 250);
            escenarioPrincipal.setTitle("Contador de pulsaciones");
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();

            Stage escenarioSecundario=new Stage();

            FXMLLoader fxmlLoader2 = new FXMLLoader(ContadorPulsaciones.class.getResource("hello-view.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 440, 250);
            escenarioSecundario.setTitle("Contador de pulsaciones");
            escenarioSecundario.setScene(scene2);
            escenarioSecundario.show();

            HelloController controlador=fxmlLoader.getController();
            HelloController controlador2=fxmlLoader2.getController();
            controlador.numProperty().bindBidirectional(controlador2.numProperty());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public IntegerProperty numProperty(){
        return numPulsaciones;
    }
}
