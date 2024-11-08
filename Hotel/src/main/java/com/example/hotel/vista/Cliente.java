package com.example.hotel.vista;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Cliente {
    private final StringProperty dni;
    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final StringProperty direccion;
    private final StringProperty localidad;
    private final StringProperty provincia;


    public Cliente() {
        this(null, null, null, null, null, null);
    }

    public Cliente(String dni, String nombre, String apellidos) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);

        this.direccion = new SimpleStringProperty("some street");
        this.localidad = new SimpleStringProperty("1234");
        this.provincia = new SimpleStringProperty("some city");
    }

    public Cliente(String dni, String nombre, String apellidos, String direccion, String localidad, String provincia) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.provincia = new SimpleStringProperty(provincia);

    }

    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public StringProperty dniProperty() {
        return dni;
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

    public String getApellidos() {
        return apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad.get();
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public StringProperty localidadProperty() {
        return localidad;
    }

    public String getProvincia() {
        return provincia.get();
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }

    public StringProperty provinciaProperty() {
        return provincia;
    }
}
