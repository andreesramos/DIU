package com.example.hotel.controller;

import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.modelo.utilidad.DateUtil;
import com.example.hotel.vista.Reserva;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
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
    @FXML
    private RadioButton alojamientoYDesayunoButton, mediaPensionButton, pensionCompletaButton;
    @FXML
    private ToggleGroup botones;

    private Stage dialogStage;
    private Reserva reserva=new Reserva();
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        ArrayList<ReservaVO> reservas = HotelModelo.obtenerReservas();

        tipoHabitacionChoice.setItems(FXCollections.observableArrayList("Doble individual", "Doble", "Junior suite", "Suite"));
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

        botones=new ToggleGroup();
        for(String opc: reserva.getRegimenOpciones()){
            RadioButton radioButton = new RadioButton(opc);
            radioButton.setToggleGroup(botones);
            if(opc.equals(reserva.getAlojamientoSeleccionado())){
                radioButton.setSelected(true);
            }
            alojamientoBox.getChildren().add(radioButton);
        }
        botones.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                RadioButton radioButton = (RadioButton) newValue;
                List<String> lista = Collections.singletonList(radioButton.getText());
                reserva.setAlojamientoSeleccionado(lista);
            }
        });
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

            RadioButton selectedAlojamiento = (RadioButton) botones.getSelectedToggle();
            if(selectedAlojamiento != null){
                reserva.setAlojamientoSeleccionado(Collections.singletonList(selectedAlojamiento.getText()));
            }else{
                System.out.println("Alojamiento nulo");
            }

            /*List<String> alojamientoSeleccionado = new ArrayList<>();
            for (Node node : alojamientoBox.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()) {
                        alojamientoSeleccionado.add(checkBox.getText());
                    }
                }
            }
            reserva.setAlojamientoSeleccionado(alojamientoSeleccionado);*/

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

        /*if (!fumadorCheck.isSelected() || fumadorCheck.isIndeterminate()) {
            errorMessage += "Fumador no valido\n";
        }*/

        if (botones.getSelectedToggle() == null) {
            errorMessage += "Régimen de alojamiento no válido\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos inválidos");
            alert.setHeaderText("Algún campo es inválido");
            alert.setContentText("Porfavor, corrija los campos");

            alert.showAndWait();
            return false;
        }
    }
}
