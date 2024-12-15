package com.example.hotel.controller;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.vista.Cliente;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ClienteEditDialogController {

    public ClienteEditDialogController() {
    }

    @FXML
    private TextField dniField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField localidadField;
    @FXML
    private TextField provinciaField;


    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        ArrayList<ClienteVO> clientes = HotelModelo.obtenerClientes();

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        dniField.setText(cliente.getDni());
        if(cliente.getDni() != null) {
            dniField.setDisable(true);
        }
        nombreField.setText(cliente.getNombre());
        apellidosField.setText(cliente.getApellidos());
        direccionField.setText(cliente.getDireccion());
        localidadField.setText(cliente.getLocalidad());
        provinciaField.setText(cliente.getProvincia());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            cliente.setDni(dniField.getText());
            cliente.setNombre(nombreField.getText());
            cliente.setApellidos(apellidosField.getText());
            cliente.setDireccion(direccionField.getText());
            cliente.setLocalidad(localidadField.getText());
            cliente.setProvincia(provinciaField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if(dniField.getText() == null || "".equals(dniField.getText())) {
            errorMessage += "Dni no valido\n";
        }

        if(!dniField.getText().matches("\\d{8}[A-Z]")){
            errorMessage += "Dni no valido\n";
        }

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre no valido\n";
        }
        if (apellidosField.getText() == null || apellidosField.getText().length() == 0) {
            errorMessage += "Apellidos no validos\n";
        }
        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "Direccion no valida\n";
        }

        if (localidadField.getText() == null || localidadField.getText().length() == 0) {
            errorMessage += "localidad no valida\n";
        }

        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "Provincia no valida\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos inválidos");
            alert.setHeaderText("Algún campo es inválido");
            alert.setContentText("Porfavor, corrija los campos");

            alert.showAndWait();
            return false;
        }
    }

}
