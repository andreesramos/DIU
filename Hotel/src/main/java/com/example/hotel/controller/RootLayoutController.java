package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.vista.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class RootLayoutController {

    private MainApp mainApp=new MainApp();

    @FXML
    private BorderPane rootLayout;

    public int obtenerDobles(){
        int contador = 0;

        for (Reserva reserva : mainApp.getReservaData()) {
            if ("doble".equalsIgnoreCase(reserva.getTipoHabitacion())) {
                contador++;
            }
        }

        return contador;
    }

    public int obtenerDoblesIndividuales(){
        int contador = 0;

        for (Reserva reserva : mainApp.getReservaData()) {
            if ("doble individual".equalsIgnoreCase(reserva.getTipoHabitacion())) {
                contador++;
            }
        }

        return contador;
    }

    public int obtenerJuniorSuite(){
        int contador = 0;

        for (Reserva reserva : mainApp.getReservaData()) {
            if ("junior suite".equalsIgnoreCase(reserva.getTipoHabitacion())) {
                contador++;
            }
        }

        return contador;
    }

    public int obtenerSuite(){
        int contador = 0;

        for (Reserva reserva : mainApp.getReservaData()) {
            if ("suite".equalsIgnoreCase(reserva.getTipoHabitacion())) {
                contador++;
            }
        }

        return contador;
    }

    @FXML
    private void handleshowOcupationStatistics(){
        mainApp.showOcupationStatistics();
    }

    @FXML
    private void handleShowRoomDoble(){
        mainApp.showRoomDoble(obtenerDobles());
    }

    @FXML
    private void handleShowRoomDobleIndividual(){
        mainApp.showRoomDobleIndividual(obtenerDoblesIndividuales());
    }

    @FXML
    private void handleShowRoomJuniorSuite(){
        mainApp.showRoomJuniorSuite(obtenerJuniorSuite());
    }

    @FXML
    private void handleShowRoomSuite(){
        mainApp.showRoomSuite(obtenerSuite());
    }

    @FXML
    private void handleShowWebView() {
        mainApp.showWebView();
    }

}
