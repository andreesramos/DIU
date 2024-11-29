package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.vista.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClienteOverviewController {
    private HotelModelo hotelModelo;

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    @FXML
    private TableView<Cliente> clienteTable;
    @FXML
    private TableColumn<Cliente, String> apellidosColumn;
    @FXML
    private TableColumn<Cliente, String> nombreColumn;

    @FXML
    private Label dniLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label localidadLabel;
    @FXML
    private Label provinciaLabel;

    private MainApp mainApp;


    public ClienteOverviewController() {
    }


    @FXML
    private void initialize() {
        apellidosColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        apellidosColumn.setSortable(true);
        nombreColumn.setSortable(true);

        showClienteDetails(null);

        clienteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClienteDetails(newValue));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        clienteTable.setItems(mainApp.getClienteData());
    }


    private void showClienteDetails(Cliente cliente) {
        if (cliente != null) {
            dniLabel.setText(cliente.getDni());
            nombreLabel.setText(cliente.getNombre());
            apellidosLabel.setText(cliente.getApellidos());
            direccionLabel.setText(cliente.getDireccion());
            localidadLabel.setText(cliente.getLocalidad());
            provinciaLabel.setText(cliente.getProvincia());
        } else {
            dniLabel.setText("");
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
        }
    }

    @FXML
    private void handleConsultarReserva(){
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();
        if(selectedCliente != null) {
            mainApp.showReservaOverview(selectedCliente);
        }else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Client Selected");
            alert.setContentText("Please select a client in the table.");
            alert.showAndWait();
        }

    }

    @FXML
    private void handleDeleteCliente() {
        int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String cod= clienteTable.getItems().get(selectedIndex).getDni();
            clienteTable.getItems().remove(selectedIndex);
            hotelModelo.eliminarCliente(cod);
        } else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Client Selected");
            alert.setContentText("Please select a client in the table.");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleNewCliente() {
        Cliente tempCliente = new Cliente();
        boolean okClicked = mainApp.showClienteEditDialog(tempCliente);
        if (okClicked) {
            mainApp.getClienteData().add(tempCliente);
            hotelModelo.insertarCliente(tempCliente);
        }
    }


    @FXML
    private void handleEditCliente() {
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = mainApp.showClienteEditDialog(selectedCliente);
            if (okClicked) {
                showClienteDetails(selectedCliente);
                hotelModelo.modificarCliente(selectedCliente);
            }

        } else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Client Selected");
            alert.setContentText("Please select a client in the table.");
            alert.showAndWait();
        }
    }
}
