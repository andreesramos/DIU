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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReservaEditDialogController {

    private int doblesIndividualReservadas = 0;
    private int doblesReservadas = 0;
    private int juniorSuitesReservadas = 0;
    private int suitesReservadas = 0;

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
    @FXML
    private Label fumadorLabel;

    private Stage dialogStage;
    private Reserva reserva=new Reserva();
    private boolean okClicked = false;
    private boolean modificar;

    @FXML
    private void initialize() {
        ArrayList<ReservaVO> reservas = HotelModelo.obtenerReservas();

        fumadorCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                fumadorLabel.setText("En virtud de la ley de sanidad se informa a los clientes de que solo podrán fumar en las habitaciones reservadas para tal fin.");
            }else{
                fumadorLabel.setText("");
            }
        });
        if(!fumadorCheck.isSelected()){
            fumadorLabel.setText("");
        }

        tipoHabitacionChoice.setItems(FXCollections.observableArrayList("Doble individual", "Doble", "Junior suite", "Suite"));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setModificar(boolean modificar) {this.modificar = modificar;}

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
                reserva.setAlojamiento(lista.getFirst());
            }
        });
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid(modificar)) {
            reserva.setFechaEntrada(fechaEntradaPicker.getValue());
            reserva.setFechaSalida(fechaSalidaPicker.getValue());


            if(spinnerHabitaciones.getValue() != null) {
                reserva.setNumHabitaciones(Integer.parseInt(spinnerHabitaciones.getValue().toString()));
            }

            reserva.setTipoHabitacion(tipoHabitacionChoice.getValue().toString());

            if(modificar){
                if(reserva.getTipoHabitacion().equals("Doble individual")){
                    doblesIndividualReservadas--;
                } else if (reserva.getTipoHabitacion().equals("Doble")) {
                    doblesReservadas--;
                } else if (reserva.getTipoHabitacion().equals("Junior suite")) {
                    juniorSuitesReservadas--;
                } else if (reserva.getTipoHabitacion().equals("Suite")) {
                    suitesReservadas--;
                }
            }

            if(reserva.getTipoHabitacion().equals("Doble individual")){
                doblesIndividualReservadas++;
            } else if (reserva.getTipoHabitacion().equals("Doble")) {
                doblesReservadas++;
            } else if (reserva.getTipoHabitacion().equals("Junior suite")) {
                juniorSuitesReservadas++;
            } else if (reserva.getTipoHabitacion().equals("Suite")) {
                suitesReservadas++;
            }

            reserva.setFumador(fumadorCheck.isSelected());

            if (botones.getSelectedToggle() != null) {
                RadioButton selectedAlojamiento = (RadioButton) botones.getSelectedToggle();
                reserva.setAlojamientoSeleccionado(Collections.singletonList(selectedAlojamiento.getText()));
            } else {
                reserva.setAlojamientoSeleccionado(Collections.singletonList("Ninguno"));
            }

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid(boolean modificar) {
        String errorMessage = "";

        LocalDate fechaEntrada = fechaEntradaPicker.getValue();
        LocalDate fechaSalida = fechaSalidaPicker.getValue();

        if (fechaEntrada == null) {
            errorMessage += "Fecha de entrada no valida\n";
        }

        if(fechaEntrada .isBefore(LocalDate.now())){
            errorMessage += "Fecha de entrada no valida\n";
        }

        if (fechaSalida == null) {
            errorMessage += "Fecha de salida no valida\n";
        }

        if(fechaSalida.isBefore(fechaEntrada)){
            errorMessage += "Fecha de salida no valida\n";
        }

        if(modificar && fechaSalida.isBefore(LocalDate.now())) {
            errorMessage += "Fecha de salida no valida\n";
        }

        if (spinnerHabitaciones.getValue() == null || (Integer)spinnerHabitaciones.getValue() <= 0) {
            errorMessage += "Numero de habitaciones no valido\n";
        }

        String tipoHabitacion=tipoHabitacionChoice.getValue().toString();
        if (tipoHabitacionChoice.getValue() == null || tipoHabitacion.isEmpty()) {
            errorMessage += "Tipo de habitacion no valida\n";
        }else if (tipoHabitacion.equals("Doble individual") && doblesIndividualReservadas>20){
            errorMessage += "No hay habitaciones disponibles\n";
        }else if (tipoHabitacion.equals("Doble") && doblesReservadas>80) {
            errorMessage += "No hay habitaciones disponibles\n";
        }else if (tipoHabitacion.equals("Junior suite") && juniorSuitesReservadas>15){
            errorMessage += "No hay habitaciones disponibles\n";
        }else if (tipoHabitacion.equals("Suite") && suitesReservadas>5){
            errorMessage += "No hay habitaciones disponibles\n";
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
