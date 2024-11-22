package com.example.andresramosjavafx.controller;

import com.example.andresramosjavafx.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {
    private MainApp mainApp= new MainApp();

    @FXML
    private void handleShowImagen() {
        mainApp.showImagen();
    }
}
