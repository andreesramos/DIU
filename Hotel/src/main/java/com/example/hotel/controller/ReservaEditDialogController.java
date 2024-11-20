package com.example.hotel.controller;

import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.modelo.utilidad.DateUtil;
import com.example.hotel.vista.Reserva;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ReservaEditDialogController {

    public ReservaEditDialogController() {
    }

    @FXML
    private DatePicker fechaEntradaPicker;
    @FXML
    private DatePicker fechaSalidaPicker;
    @FXML
    private Spinner spinnerHabitaciones;
    @FXML
    private ChoiceBox tipoHabitacionChoice;
    @FXML
    private CheckBox fumadorCheck;
    @FXML
    private VBox alojamientoBox;
    /*@FXML
    private ProgressBar progreso;
    @FXML
    private ProgressIndicator indicator;*/

    private Stage dialogStage;
    private Reserva reserva;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        ArrayList<ReservaVO> reservas = HotelModelo.obtenerReservas();
        //cambiarBarra(reservas.size());
        //progreso.progressProperty().bindBidirectional(progresoNum);
        //indicator.progressProperty().bindBidirectional(progresoNum);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;

        fechaEntradaPicker.setValue(reserva.getFechaEntrada());
        fechaSalidaPicker.setValue(reserva.getFechaSalida());
        if(spinnerHabitaciones.getValueFactory() != null) {
            spinnerHabitaciones.getValueFactory().setValue(reserva.getNumHabitaciones());
        }
        tipoHabitacionChoice.setValue(reserva.getTipoHabitacion());
        fumadorCheck.setSelected(reserva.getFumador());
        alojamientoBox.getChildren().clear();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            reserva.setFechaEntrada(fechaEntradaPicker.getValue());
            reserva.setFechaSalida(fechaSalidaPicker.getValue());
            if(spinnerHabitaciones.getValue() != null) {
                reserva.setNumHabitaciones(Integer.parseInt(spinnerHabitaciones.getValue().toString()));
            }
            reserva.setTipoHabitacion(tipoHabitacionChoice.getValue().toString());
            reserva.setFumador(fumadorCheck.isSelected());

            List<String> alojamientoSeleccionado = new ArrayList<>();
            for (Node node : alojamientoBox.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()) {
                        alojamientoSeleccionado.add(checkBox.getText());
                    }
                }
            }
            reserva.setAlojamientoSeleccionado(alojamientoSeleccionado);

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

        if (fechaEntradaPicker.getValue() == null) {
            errorMessage += "Fecha de entrada no valida\n";
        }

        if (fechaSalidaPicker.getValue() == null) {
            errorMessage += "Fecha de salida no valida\n";
        }

        if (spinnerHabitaciones.getValue() == null || (Integer)spinnerHabitaciones.getValue() <= 0) {
            errorMessage += "Numero de habitaciones no valido\n";
        }

        if (tipoHabitacionChoice.getValue() == null || tipoHabitacionChoice.getValue().toString().isEmpty()) {
            errorMessage += "Tipo de habitacion no valida\n";
        }

        if (!fumadorCheck.isSelected() || fumadorCheck.isIndeterminate()) {
            errorMessage += "Fumador no valido\n";
        }

        boolean isAlojamientoSeleccionado = false;
        for (Node node : alojamientoBox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    isAlojamientoSeleccionado = true;
                    break;
                }
            }
        }
        if (!isAlojamientoSeleccionado) {
            errorMessage += "Regimen de alojamiento no valido\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nada Seleccionado");
            alert.setHeaderText("No hay reserva seleccionada");
            alert.setContentText("Porfavor, seleccione una reserva");

            alert.showAndWait();
            return false;
        }
    }
}
