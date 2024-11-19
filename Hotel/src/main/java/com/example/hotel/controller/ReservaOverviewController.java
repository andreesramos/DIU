package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.utilidad.DateUtil;
import com.example.hotel.vista.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ReservaOverviewController {
    private HotelModelo hotelModelo;
    private Stage dialogStage;

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    @FXML
    private TableView<Reserva> reservaTable;
    @FXML
    private TableColumn<Reserva, String> idReservaColumn;
    @FXML
    private TableColumn<Reserva, String> fechaEntradaColumn;

    @FXML
    private Label fechaEntradaLabel;
    @FXML
    private Label fechaSalidaLabel;
    @FXML
    private Label numHabitacionesLabel;
    @FXML
    private Label tipoHabitacionLabel;
    @FXML
    private Label fumadorLabel;
    @FXML
    private Label alojamientoLabel;

    private MainApp mainApp;

    public ReservaOverviewController() {
    }

    @FXML
    private void initialize() {
        idReservaColumn.setCellValueFactory(cellData -> cellData.getValue().idReservaProperty().asString());
        fechaEntradaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaEntradaProperty().asString());

        showReservaDetails(null);

        reservaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReservaDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        reservaTable.setItems(mainApp.getReservaData());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void showReservaDetails(Reserva reserva) {
        if (reserva != null) {
            // Fill the labels with info from the reserva object.
            fechaEntradaLabel.setText(DateUtil.format(reserva.getFechaEntrada()));
            fechaSalidaLabel.setText(reserva.getFechaSalida().toString());
            numHabitacionesLabel.setText(reserva.getNumHabitaciones().toString());
            tipoHabitacionLabel.setText(reserva.getTipoHabitacion());
            fumadorLabel.setText(reserva.getFumador().toString());
            alojamientoLabel.setText(reserva.getAlojamiento());
        } else {
            // Person is null, remove all the text.
            fechaEntradaLabel.setText("");
            fechaSalidaLabel.setText("");
            numHabitacionesLabel.setText("");
            tipoHabitacionLabel.setText("");
            fumadorLabel.setText("");
            alojamientoLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteReserva() {
        int selectedIndex = reservaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Integer cod=reservaTable.getItems().get(selectedIndex).getIdReserva();
            reservaTable.getItems().remove(selectedIndex);
            hotelModelo.eliminarReserva(cod);
        } else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nada Seleccionado");
            alert.setHeaderText("No hay reserva seleccionada");
            alert.setContentText("Porfavor, seleccione una reserva");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewReserva() {
        Reserva tempReserva = new Reserva();
        boolean okClicked = mainApp.showReservaEditDialog(tempReserva);
        if (okClicked) {
            mainApp.getReservaData().add(tempReserva);
            hotelModelo.insertarReserva(tempReserva);
        }
    }

    @FXML
    private void handleEditReserva() {
        Reserva selectedReserva = reservaTable.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            boolean okClicked = mainApp.showReservaEditDialog(selectedReserva);
            if (okClicked) {
                showReservaDetails(selectedReserva);
                hotelModelo.modificarReserva(selectedReserva);
            }

        } else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nada Seleccionado");
            alert.setHeaderText("No hay reserva seleccionada");
            alert.setContentText("Porfavor, seleccione una reserva");
            alert.showAndWait();
        }
    }
}
