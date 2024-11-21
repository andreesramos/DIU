package com.example.examen1danielgomezcurso2324.model;

import javafx.beans.property.*;

public class Articulo {

        private int codigo;
        private StringProperty nombre;
        private StringProperty descripcion;
        private FloatProperty precio;
        private IntegerProperty cantidad;

        public Articulo(){
            this(null,null,null,null);
        }

        public Articulo(String nombre, String descripcion, Float precio, Integer cantidad){
            this.nombre = new SimpleStringProperty(nombre);
            this.descripcion = new SimpleStringProperty(descripcion);
            if(precio != null){
                this.precio = new SimpleFloatProperty(precio);
            } else{
                this.precio = new SimpleFloatProperty(0.0F);
            }
            if(cantidad != null){
                this.cantidad = new SimpleIntegerProperty(cantidad);
            } else{
                this.cantidad = new SimpleIntegerProperty(0);
            }
        }

        public Articulo(Float precio, Integer cantidad){
            if(precio != null){
                this.precio = new SimpleFloatProperty(precio);
            } else{
                this.precio = new SimpleFloatProperty(0.0F);
            }
            if(cantidad != null){
                this.cantidad = new SimpleIntegerProperty(cantidad);
            } else{
                this.cantidad = new SimpleIntegerProperty(0);
            }
        }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public float getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }
}
