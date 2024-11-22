package com.example.andresramosjavafx;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.andresramosjavafx.controller.ConversorViewController;
import com.example.andresramosjavafx.controller.ImagenController;
import com.example.andresramosjavafx.modelo.ConversorModelo;
import com.example.andresramosjavafx.vista.Moneda;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Moneda> monedaData = FXCollections.observableArrayList();
    ConversorModelo conversorModelo;
    MonedaRepositoryImpl monedaRepositoryImpl;

    public MainApp(){
        try {
            monedaRepositoryImpl = new MonedaRepositoryImpl();
            conversorModelo = new ConversorModelo();
            conversorModelo.setMonedaRepository(monedaRepositoryImpl);
            monedaData.addAll(conversorModelo.mostrarMonedas());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Moneda> getMonedaData() {
        return monedaData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CONVERSOR");

        initRootLayout();

        showMonedaOverview();

    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMonedaOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("conversorView.fxml"));
            AnchorPane conversorOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(conversorOverview);

            ConversorViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setConversorModelo(conversorModelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showImagen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Imagen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ArrayList<MonedaVO> listaMonedaVO = new ArrayList<MonedaVO>();

            try{
                listaMonedaVO = conversorModelo.obtenerMonedas();
                conversorModelo.setNumeroMonedas(listaMonedaVO.size());
            }catch (ExcepcionMoneda e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al listar las monedas.");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos");
                alert.showAndWait();
            }

            ImagenController controller = loader.getController();
            controller.setNumeroMonedas(listaMonedaVO.size());

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
