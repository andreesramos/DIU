package com.example.examen1danielgomezcurso2324.controller;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import com.example.examen1danielgomezcurso2324.model.ModeloArticulos;
import com.example.examen1danielgomezcurso2324.model.Repository.ArticuloRepositoryJDBC;
import com.example.examen1danielgomezcurso2324.MainApp;
import com.example.examen1danielgomezcurso2324.model.Articulo;
import com.example.examen1danielgomezcurso2324.util.ConversorArticulo;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class CatalogoOverviewController {
    private MainApp mainApp;
    private ConversorArticulo conversorArticulo;
    private ModeloArticulos modeloArticulos;
    private Articulo articuloSeleccionado;

    @FXML
    private TableView<Articulo> tablaArticulo;
    @FXML
    private TableColumn<Articulo, String> nombreColumna;
    @FXML
    private TableColumn<Articulo, Float> precioColumna;
    @FXML
    private Label nombreProducto;
    @FXML
    private Label descripcionProducto;
    @FXML
    private Label precioProducto;
    @FXML
    private Label cantidadProducto;
    @FXML
    private TextField textFieldUnidades;
    @FXML
    private TextField textFieldTotal;
    private KeyEvent enter;

    public CatalogoOverviewController() {
    }

    @FXML
    public void initialize() {
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        precioColumna.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        tablaArticulo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    mostrarDetallesProductos(newValue);
                    articuloSeleccionado = new Articulo(newValue.getPrecio(), newValue.getCantidad());
                }
        );


    }

    public void mostrarDetallesProductos(Articulo articulo) {
        if (articulo != null) {
            nombreProducto.setText(articulo.getNombre());
            descripcionProducto.setText(articulo.getDescripcion());
            precioProducto.setText(String.valueOf(articulo.getPrecio()));
            cantidadProducto.setText(String.valueOf(articulo.getCantidad()));
        } else {
            nombreProducto.setText("");
            descripcionProducto.setText("");
            precioProducto.setText("");
            cantidadProducto.setText("");
        }
    }

    public void handleNewProduct() {
        Articulo articulo = new Articulo();
        boolean okClicked = mainApp.showNuevoProducto(articulo);
        if (okClicked) {
            try {
                ArticuloVO articuloVO = conversorArticulo.convertirArticuloVO(articulo);
                modeloArticulos.addArticulo(articuloVO);
                mainApp.getListaArticulos().add(articulo);
                modeloArticulos.incrementarN();
            } catch (ExcepcionArticulo e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Error");
                alert.setTitle("Error al crear una persona.");
                alert.setContentText("Error al listar los productos.");
                alert.showAndWait();
            }
        }
    }

    public void handleTotal() {
        if (articuloSeleccionado != null) {
            try {
                Integer unidades = Integer.parseInt(textFieldUnidades.getText());
                if (unidades <= articuloSeleccionado.getCantidad() && unidades >= 0) {
                    textFieldTotal.setText(String.valueOf(unidades * articuloSeleccionado.getPrecio()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al obtener el precio total");
                    alert.setTitle("Error");
                    alert.setContentText("Escriba una cantidad menor o igual a la disponible");
                    textFieldTotal.setText("");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al obtener el precio total");
                alert.setTitle("Error");
                alert.setContentText("Escriba un número");
                textFieldTotal.setText("");
                alert.showAndWait();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al obtener el precio total");
            alert.setTitle("Error");
            alert.setContentText("Seleccione un producto");
            textFieldTotal.setText("");
            alert.showAndWait();
        }
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tablaArticulo.setItems(mainApp.getListaArticulos());
    }

    public void setConversorArticulo(ConversorArticulo conversorArticulo) {
        this.conversorArticulo = conversorArticulo;
    }

    public void setModeloArticulos(ModeloArticulos modeloArticulos) {
        this.modeloArticulos = modeloArticulos;
    }
}
