package com.example.hotel.modelo;

import java.time.LocalDate;

public class ClienteVO {
    String dni;
    String nombre;
    String apellidos;
    String direccion;
    String localidad;
    String provincia;

    public ClienteVO() {
        this(null, null, null, null, null, null);
    }

    public ClienteVO(String dni, String nombre, String apellido, String direccion, String localidad, String provincia) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellido;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getNombre() {return this.nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellidos() {return this.apellidos;}
    public void setApellidos(String apellido) {this.apellidos = apellido;}

    public String getDireccion() {return this.direccion;}
    public void setDireccion(String calle) {this.direccion = calle;}

    public String getLocalidad() {return this.localidad;}
    public void setLocalidad(String codigoPostal) {this.localidad = codigoPostal;}

    public String getProvincia() {return this.provincia;}
    public void setProvincia(String ciudad) {this.provincia = ciudad;}

    public String toString(){
        return "ClienteVO{dni=" + this.dni + ", nombre=" + this.nombre + ", apellido=" + this.apellidos + ", direccion=" + this.direccion + ", localidad=" + this.localidad + ", provincia=" + this.provincia + "}";
    }
}
