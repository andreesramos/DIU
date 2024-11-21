package com.example.examen1danielgomezcurso2324.model;

import Modelo.*;
import Modelo.Repository.ArticuloRepository;
import com.example.examen1danielgomezcurso2324.model.Repository.ArticuloRepositoryJDBC;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.controlsfx.control.PropertySheet;

import java.util.ArrayList;

public class ModeloArticulos {
    private ArticuloRepository articuloRepository;
    private IntegerProperty productosDisponibles = new SimpleIntegerProperty();

    public ModeloArticulos(){

    }

    public ArticuloVO consulta(int var1) throws ExcepcionArticulo{
        return articuloRepository.consulta(var1);
    }

    public void addArticulo(ArticuloVO articuloVO) throws ExcepcionArticulo{
        articuloRepository.addArticulo(articuloVO);
    }

    public ArrayList<ArticuloVO> obtenerListaArticulos() throws ExcepcionArticulo{
        return articuloRepository.obternerListaArticulos();
    }

    public void incrementarN() {
        int antiguoValor = productosDisponibles.get();
        antiguoValor ++;
        productosDisponibles.set(antiguoValor);
    }

    public void setArticuloRepositoryImpl(ArticuloRepositoryJDBC impl){
        this.articuloRepository = impl;
    }

    public int getProductosDisponibles() {
        return productosDisponibles.get();
    }

    public IntegerProperty productosDisponiblesProperty() {
        return productosDisponibles;
    }

    public void setProductosDisponibles(int productosDisponibles) {
        this.productosDisponibles.set(productosDisponibles);
    }
}
