package com.example.hotel;

import com.example.hotel.controller.ClienteEditDialogController;
import com.example.hotel.controller.ClienteOverviewController;
import com.example.hotel.controller.ReservaEditDialogController;
import com.example.hotel.controller.ReservaOverviewController;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.repository.impl.ClienteRepositoryImpl;
import com.example.hotel.modelo.repository.impl.ReservaRepositoryImpl;
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }
    public ObservableList<Reserva> getReservaData() {return reservaData;}

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

            /*Llamar al metodo de HotelModelo obtener reservas para meter en una variable las reservas
            asociadas al dni del cliente y poder pasar las reservas como parametro en setReserva
             */

            ReservaOverviewController controller = loader.getController();
            controller.setReserva(cliente);
            controller.setDialogStage(dialogStage);

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
            controller.setReserva(reserva);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*public void showOcupationStatistics() {
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
            controller.setClienteData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        launch();
    }
}