package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.modelo.utilidad.DateUtil;
import com.example.hotel.modelo.utilidad.ReservaUtil;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ReservaOverviewController {
    private HotelModelo hotelModelo;
    private Stage dialogStage;
    private MainApp mainApp=new MainApp();
    private Reserva reserva;
    private Cliente currentCliente;

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
    @FXML
    private Label dniClienteLabel;
    @FXML
    private Label nombreClienteLabel;
    @FXML
    private Label apellidosClienteLabel;

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReserva(Cliente cliente) {
        this.currentCliente = cliente;
        ObservableList<Reserva> reservasFiltradas = FXCollections.observableArrayList();

        dniClienteLabel.setText(cliente.getDni());
        nombreClienteLabel.setText(cliente.getNombre());
        apellidosClienteLabel.setText(cliente.getApellidos());

        for (Reserva reserva : mainApp.getReservaData()) {
            if (reserva.getDniCliente().equals(cliente.getDni())) {
                reservasFiltradas.add(reserva);
            }
        }

        reservaTable.setItems(reservasFiltradas);

        fechaEntradaColumn.setSortType(TableColumn.SortType.DESCENDING);
        reservaTable.getSortOrder().add(fechaEntradaColumn);

        reservaTable.sort();
    }

    private void showReservaDetails(Reserva reserva) {
        if (reserva != null) {
            fechaEntradaLabel.setText(DateUtil.format(reserva.getFechaEntrada()));
            fechaSalidaLabel.setText(DateUtil.format(reserva.getFechaSalida()));
            numHabitacionesLabel.setText(reserva.getNumHabitaciones().toString());
            tipoHabitacionLabel.setText(reserva.getTipoHabitacion());
            fumadorLabel.setText(reserva.getFumador().toString());
            alojamientoLabel.setText(reserva.getAlojamiento());
        } else {
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
        tempReserva.setDniCliente(dniClienteLabel.getText());
        boolean okClicked = mainApp.showReservaEditDialog(tempReserva);
        if (okClicked) {
            //Inserta la reserva en la base de datos
            hotelModelo.insertarReserva(tempReserva);

            //Limpia los datos de la tabla
            reservaTable.getItems().clear();

            //Recarga las reservas desde la base de datos
            ArrayList<Reserva> listaReservasBD = ReservaUtil.getReserva(hotelModelo.obtenerReservas());
            ObservableList<Reserva> reservasBD = FXCollections.observableArrayList(listaReservasBD);

            //Actualiza la lista principal y la tabla
            mainApp.getReservaData().setAll(reservasBD);
            reservaTable.setItems(mainApp.getReservaData());
            reservaTable.sort();

            /*// Agregar la nueva reserva a los datos principales
            mainApp.getReservaData().add(tempReserva);

            // Insertar la nueva reserva en la base de datos
            hotelModelo.insertarReserva(tempReserva);

            // Actualizar y ordenar el TableView
            reservaTable.refresh();
            reservaTable.sort();*/
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
                reservaTable.sort();
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
