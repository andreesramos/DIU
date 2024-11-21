package com.example.examen1danielgomezcurso2324.controller;

import com.example.examen1danielgomezcurso2324.model.Repository.ArticuloRepositoryJDBC;
import com.example.examen1danielgomezcurso2324.MainApp;
import com.example.examen1danielgomezcurso2324.model.Articulo;
import com.example.examen1danielgomezcurso2324.model.ModeloArticulos;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevoProductoController {
    private ModeloArticulos modeloArticulos;
    private MainApp mainApp;
    private Stage dialogStage;
    private Articulo articulo;
    private boolean okClicked = false;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField precioField;
    @FXML
    private TextField cantidadField;
    @FXML
    private Label nDisponible;
    private IntegerProperty productosDisponibles = new SimpleIntegerProperty();

    public NuevoProductoController() {

    }


    public void handleOk(){
        if(isInputValid()) {
            articulo.setNombre(nombreField.getText());
            articulo.setDescripcion(descripcionField.getText());
            articulo.setPrecio(Float.parseFloat(precioField.getText()));
            articulo.setCantidad(Integer.parseInt(cantidadField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    public void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0){
            errorMessage += "Nombre no válido\n";
        }
        if (descripcionField.getText() == null || descripcionField.getText().length() == 0) {
            errorMessage += "descripción no válida\n";
        }
        if (precioField.getText() == null || precioField.getText().length() == 0) {
            errorMessage += "precio no válido\n";
        } else{
            try {
                Float.parseFloat(precioField.getText());
            }catch (NumberFormatException e){
                errorMessage += "precio no válido (Debe de ser un número)\n";
            }
        }
        if (cantidadField.getText() == null || cantidadField.getText().length() == 0) {
            errorMessage += "Cantidad no válida\n";
        } else {
            try {
                Integer.parseInt(cantidadField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Cantidad no válida (Debe de ser un número)\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Por favor introduzca los datos correctamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void updateProgress(){
        productosDisponibles.bind(modeloArticulos.productosDisponiblesProperty());
        nDisponible.setText(productosDisponibles.getValue().toString());
        productosDisponibles.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                nDisponible.setText(productosDisponibles.getValue().toString());
            }
        });
    }
    public void setModeloArticulos(ModeloArticulos modeloArticulos) {
        this.modeloArticulos = modeloArticulos;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setProductosDisponibles(int productosDisponibles) {
        this.productosDisponibles.set(productosDisponibles);
    }
}
