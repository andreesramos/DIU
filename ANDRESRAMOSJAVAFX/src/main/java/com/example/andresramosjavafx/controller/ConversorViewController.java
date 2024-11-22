package com.example.andresramosjavafx.controller;

import Modelo.ExcepcionMoneda;
import com.example.andresramosjavafx.MainApp;
import com.example.andresramosjavafx.modelo.ConversorModelo;
import com.example.andresramosjavafx.vista.Moneda;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConversorViewController {

    private ConversorModelo conversorModelo;
    private Moneda moneda;

    public void setConversorModelo(ConversorModelo conversorModelo) {
        this.conversorModelo = conversorModelo;
    }

    @FXML
    private TableView<Moneda> monedaTable;
    @FXML
    private TableColumn<Moneda, String> nombreMonedaColumn;

    @FXML
    private Label nombreMonedaLabel;
    @FXML
    private TextField nombreMonedaField;
    @FXML
    private TextField eurosField;

    private MainApp mainApp;

    public ConversorViewController() {}

    @FXML
    private void initialize() {
        nombreMonedaColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        showMonedaDetails(null);

        monedaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMonedaDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        monedaTable.setItems(mainApp.getMonedaData());
    }


    private void showMonedaDetails(Moneda moneda) {
        if (moneda != null) {
            nombreMonedaLabel.setText(moneda.getNombre());
        } else {
            nombreMonedaLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteMoneda() throws ExcepcionMoneda {
        int selectedIndex = monedaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Integer cod=monedaTable.getItems().get(selectedIndex).getCodigo();
            monedaTable.getItems().remove(selectedIndex);
            conversorModelo.eliminarMoneda(cod);
        } else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nada seleccionado");
            alert.setHeaderText("Ninguna moneda seleccionada");
            alert.setContentText("Selecciona una moneda de la tabla.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleConvertirMoneda() throws ExcepcionMoneda {
        float multiplicador;
        float resultado;
        try {
            if(Integer.parseInt(nombreMonedaField.getText()) >= 0 && Integer.parseInt(eurosField.getText()) == 0) {
                multiplicador = conversorModelo.obtenerMultiplicador(nombreMonedaLabel.getText());
                resultado= (Float.parseFloat(nombreMonedaField.getText())) * (2-multiplicador);
                eurosField.setText(String.valueOf(resultado));
            }else if(Integer.parseInt(eurosField.getText()) >= 0 && Integer.parseInt(nombreMonedaField.getText()) == 0) {
                multiplicador = conversorModelo.obtenerMultiplicador(nombreMonedaLabel.getText());
                resultado = (Float.parseFloat(eurosField.getText())) * multiplicador;
                nombreMonedaField.setText(String.valueOf(resultado));
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al convertir moneda");
                alert.setTitle("Error al convertir moneda");
                alert.setContentText("Solo puede escribir en una caja de texto");
                alert.showAndWait();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al convertir moneda");
            alert.setTitle("Error al convertir moneda");
            alert.setContentText("Debe elegir una moneda");
            alert.showAndWait();
        }
    }
}