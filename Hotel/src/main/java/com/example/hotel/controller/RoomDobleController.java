package com.example.hotel.controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RoomDobleController {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;

    private Stage dialogStage;

    private DoubleProperty progressNum = new SimpleDoubleProperty();

    public RoomDobleController() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        progressBar.progressProperty().bind(progressNum);
        progressIndicator.progressProperty().bind(progressNum);
    }

    public void actualizarProgreso(int num){
        progressNum.set((double)num/80);
    }
}
