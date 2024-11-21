package com.example.examen1danielgomezcurso2324;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import com.example.examen1danielgomezcurso2324.controller.CatalogoOverviewController;
import com.example.examen1danielgomezcurso2324.controller.NuevoProductoController;
import com.example.examen1danielgomezcurso2324.model.Articulo;
import com.example.examen1danielgomezcurso2324.model.ModeloArticulos;
import com.example.examen1danielgomezcurso2324.model.Repository.ArticuloRepositoryJDBC;
import com.example.examen1danielgomezcurso2324.util.ConversorArticulo;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane articuloOverview;
    private ArticuloRepositoryJDBC impl = new ArticuloRepositoryJDBC();
    private ModeloArticulos modeloArticulos = new ModeloArticulos();
    private ConversorArticulo conversorArticulo = new ConversorArticulo();
    private ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();
    private IntegerProperty productosDisponibles = new SimpleIntegerProperty();

    public MainApp(){
        listaArticulos.addAll(obtenerLista());
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestor catálogo");

        showArticuloOverview();
    }

    public ArrayList<Articulo> obtenerLista(){
        modeloArticulos.setArticuloRepositoryImpl(impl);
        ArrayList<Articulo> listaArticulos = new ArrayList<>();
        try {
            ArrayList<ArticuloVO> listaVO = new ArrayList<>(modeloArticulos.obtenerListaArticulos());
            listaArticulos.addAll(conversorArticulo.convertirLista(listaVO));
            productosDisponibles.set(listaArticulos.size());
            modeloArticulos.setProductosDisponibles(productosDisponibles.getValue());
        } catch (ExcepcionArticulo e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setTitle("Error");
            alert.setContentText("Error al listar los productos.");
            alert.showAndWait();        }
        return listaArticulos;
    }

    public void showArticuloOverview(){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ArticuloOverview.fxml"));
        articuloOverview = (AnchorPane) loader.load();

        Scene scene = new Scene(articuloOverview);
        primaryStage.setScene(scene);
        primaryStage.show();

        CatalogoOverviewController controller = loader.getController();
        controller.setMainApp(this);
        controller.setModeloArticulos(modeloArticulos);
        controller.setConversorArticulo(conversorArticulo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean showNuevoProducto(Articulo articulo){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("NuevoProductoDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Añadir artículo");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            NuevoProductoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setModeloArticulos(modeloArticulos);
            controller.updateProgress();
            controller.setArticulo(articulo);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Articulo> getListaArticulos() {
        return listaArticulos;
    }
}
