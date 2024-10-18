package com.example.conversor;

import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.conversor.controller.ConversorController;
import com.example.conversor.modelo.ConversorModelo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ConversorMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {

        try {
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            ConversorModelo conversorModelo = new ConversorModelo();
            conversorModelo.setMonedaRepository(monedarepositoryImpl);

            FXMLLoader fxmlLoader = new FXMLLoader(ConversorMain.class.getResource("conversorView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            escenarioPrincipal.setTitle("Conversor de moneda");
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();


            ConversorController conversorController = fxmlLoader.getController();
            conversorController.setConversorModelo(conversorModelo);
        }catch (Exception e){
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se ha podido encontrar la tasa de cambio");
            alert.setContentText("Inténtelo más tarde");
            alert.showAndWait();
        }

    }
}
