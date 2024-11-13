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
        // Initialize the person table with the two columns.
        apellidosColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        // Clear person details
        showClienteDetails(null);

        // Listen for selection changes and show the person details when changed.
        clienteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClienteDetails(newValue));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        clienteTable.setItems(mainApp.getClienteData());
    }


    private void showClienteDetails(Cliente cliente) {
        if (cliente != null) {
            // Fill the labels with info from the cliente object.
            dniLabel.setText(cliente.getNombre());
            nombreLabel.setText(cliente.getApellidos());
            apellidosLabel.setText(cliente.getDireccion());
            direccionLabel.setText(cliente.getLocalidad());
            localidadLabel.setText(cliente.getProvincia());
        } else {
            // Person is null, remove all the text.
            dniLabel.setText("");
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
        }
    }


    private void handleDeleteCliente() {
        int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String cod= clienteTable.getItems().get(selectedIndex).getDni();
            clienteTable.getItems().remove(selectedIndex);
            hotelModelo.eliminarCliente(cod);
        } else {
            // Nothing selected.
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleNewCliente() {
        Cliente tempCliente = new Cliente();
        //Corregir error de abajo
        /*boolean okClicked = mainApp.showClienteEditDialog(tempCliente);
        if (okClicked) {
            mainApp.getClienteData().add(tempCliente);
            hotelModelo.insertarCliente(tempCliente);
        }*/
    }


    @FXML
    private void handleEditPerson() {
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            //Corregir error de abajo
            /*boolean okClicked = mainApp.showClienteEditDialog(selectedCliente);
            if (okClicked) {
                showClienteDetails(selectedCliente);
                hotelModelo.modificarCliente(selectedCliente);
            }*/

        } else {
            // Nothing selected.
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Client Selected");
            alert.setContentText("Please select a client in the table.");
            alert.showAndWait();
        }
    }
}
