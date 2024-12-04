package com.example.hotel;

import com.example.hotel.controller.*;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.modelo.repository.impl.ClienteRepositoryImpl;
import com.example.hotel.modelo.repository.impl.ReservaRepositoryImpl;
import com.example.hotel.modelo.utilidad.ReservaUtil;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();
    HotelModelo hotelModelo;

    public MainApp(){
        try {
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
            ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
            hotelModelo = new HotelModelo();
            hotelModelo.setClienteRepository(clienteRepository);
            hotelModelo.setReservaRepository(reservaRepository);

            clienteData.addAll(hotelModelo.mostrarClientes());
            reservaData.addAll(hotelModelo.mostrarReservas());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setReservaData(ObservableList<Reserva> reservaData){this.reservaData = reservaData;};
    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }
    public ObservableList<Reserva> getReservaData() {return reservaData;}

    public ObservableList<Reserva> getReservaData(Cliente cliente){
        ObservableList<Reserva> reservas = FXCollections.observableArrayList();
        for (Reserva reserva : reservaData){
            if(reserva.getDniCliente().equals(cliente.getDni())){
                reservas.add(reserva);
            }
        }
        return reservas;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Hotel");

        initRootLayout();
        showClienteOverview();

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

    public void showClienteOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClienteOverview.fxml"));
            AnchorPane clienteOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(clienteOverview);

            ClienteOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setHotelModelo(hotelModelo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showClienteEditDialog(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClienteEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Cliente");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ClienteEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showReservaOverview(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ReservaOverview.fxml"));
            AnchorPane reservaOverview = (AnchorPane) loader.load();

            //rootLayout.setCenter(reservaOverview);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Consultar Reserva");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(reservaOverview);
            dialogStage.setScene(scene);

            ReservaOverviewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setHotelModelo(hotelModelo);
            controller.setReserva(cliente);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showReservaEditDialog(Reserva reserva) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ReservaEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Reserva");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ReservaEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setModificar(true);
            controller.setReserva(reserva);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showOcupationStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("OcupationStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Estadísticas de Ocupación");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            OcupationStatisticsController controller = loader.getController();
            controller.setReservaData(reservaData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRoomDoble(int numHab){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RoomDoble.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Habitación Doble");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RoomDobleController controller = loader.getController();
            controller.actualizarProgreso(numHab);

            dialogStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showRoomDobleIndividual(int numHab){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RoomDobleIndividual.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Habitación Doble Individual");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RoomDobleIndividualController controller = loader.getController();
            controller.actualizarProgreso(numHab);

            dialogStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showRoomJuniorSuite(int numHab){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RoomJuniorSuite.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Habitación Junior Suite");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RoomJuniorSuiteController controller = loader.getController();
            controller.actualizarProgreso(numHab);

            dialogStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showRoomSuite(int numHab){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RoomSuite.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Habitación Suite");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RoomSuiteController controller = loader.getController();
            controller.actualizarProgreso(numHab);

            dialogStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showWebView(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("WebView.fxml"));
            BorderPane page = (BorderPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("WEBVIEW");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            WebViewController controller = loader.getController();
            controller.mostrarWebView();

            dialogStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}