package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.vista.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TextField dniBuscar;
    @FXML
    private Button buscarButton;

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

        showClienteDetails(null);

        clienteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClienteDetails(newValue));
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        clienteTable.setItems(mainApp.getClienteData());

        apellidosColumn.setSortType(TableColumn.SortType.ASCENDING);
        clienteTable.getSortOrder().add(apellidosColumn);

        clienteTable.sort();
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
            clienteTable.sort();
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
                clienteTable.sort();
            }

        } else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Client Selected");
            alert.setContentText("Please select a client in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBuscarCliente() {
        String dni = dniBuscar.getText();

        if(dni.isEmpty()) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("BUSQUEDA VÁCIA");
            alert.setHeaderText("Introduce DNI");
            alert.setContentText("Introduce un DNI para buscar");
            alert.showAndWait();
        }else {
            try {
                Cliente cliente = hotelModelo.buscarCliente(dni);
                if (cliente != null) {
                    boolean okClicked = mainApp.showClienteEditDialog(cliente);
                    if (okClicked) {
                        showClienteDetails(cliente);
                        hotelModelo.modificarCliente(cliente);
                        clienteTable.sort();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("DNI no encontrado");
                    alert.setHeaderText("Ningun cliente con ese dni");
                    alert.setContentText("No existe ningun cliente con ese dni");
                    alert.showAndWait();
                }
            } catch (ExcepcionHotel e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
