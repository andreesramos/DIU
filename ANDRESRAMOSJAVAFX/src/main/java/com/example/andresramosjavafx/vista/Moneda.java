package com.example.andresramosjavafx.vista;

import javafx.beans.property.*;

public class Moneda {

    private final IntegerProperty codigo;
    private final StringProperty nombre;
    private final FloatProperty multiplicador;

    public Moneda() {
        this(0, null, null);
    }

    public Moneda(Integer codigo, String nombre, Float multiplicador) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleFloatProperty(multiplicador);
    }

    public Integer getCodigo() {
        return codigo.get();
    }

    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public Float getMultiplicador() {
        return multiplicador.get();
    }

    public void setMultiplicador(Float multiplicador) {
        this.multiplicador.set(multiplicador);
    }

    public FloatProperty multiplicadorProperty() {
        return multiplicador;
    }
}
