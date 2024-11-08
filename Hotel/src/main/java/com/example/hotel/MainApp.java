package com.example.hotel;

import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.repository.impl.ClienteRepositoryImpl;
import com.example.hotel.modelo.repository.impl.ReservaRepositoryImpl;
import com.example.hotel.vista.Cliente;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> personData = FXCollections.observableArrayList();
    HotelModelo hotelModelo;

    public MainApp(){
        try {
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
            ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
            hotelModelo = new HotelModelo();
            hotelModelo.setClienteRepository(clienteRepository);
            hotelModelo.setReservaRepository(reservaRepository);
            System.out.println(hotelModelo.obtenerClientes());
            System.out.println(hotelModelo.obtenerReservas());
            //personData.addAll(hotelModelo.mostrarPersonas());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("Hotel");

    }

    public static void main(String[] args) {
        launch();
    }
}