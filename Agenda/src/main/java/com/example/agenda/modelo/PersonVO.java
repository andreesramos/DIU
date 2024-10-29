package com.example.agenda.modelo;

import java.time.LocalDate;

public class PersonVO {
    int codigo;
    String firstName;
    String lastName;
    String street;
    String postalCode;
    String city;
    LocalDate birthday;

    /*public PersonVO(String nombre, String apellido, String calle, String codigoPostal, String ciudad, LocalDate fechaNacimiento) {
        this.firstName = nombre;
        this.lastName = apellido;
        this.street = calle;
        this.postalCode = codigoPostal;
        this.city = ciudad;
        this.birthday = fechaNacimiento;
    }*/

    public PersonVO() {
        this(0, null, null, null, null, null, null);
    }

    public PersonVO(int codigo, String nombre, String apellido, String calle, String codigoPostal, String ciudad, LocalDate fechaNacimiento) {
        this.codigo = codigo;
        this.firstName = nombre;
        this.lastName = apellido;
        this.street = calle;
        this.postalCode = codigoPostal;
        this.city = ciudad;
        this.birthday = fechaNacimiento;
    }

    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}

    public String getFirstName() {return this.firstName;}
    public void setFirstName(String nombre) {this.firstName = nombre;}

    public String getLastName() {return this.lastName;}
    public void setLastName(String apellido) {this.lastName = apellido;}

    public String getStreet() {return this.street;}
    public void setStreet(String calle) {this.street = calle;}

    public String getPostalCode() {return this.postalCode;}
    public void setPostalCode(String codigoPostal) {this.postalCode = codigoPostal;}

    public String getCity() {return this.city;}
    public void setCity(String ciudad) {this.city = ciudad;}

    public LocalDate getBirthday() {return this.birthday;}
    public void setBirthday(LocalDate fechaNacimiento) {this.birthday = fechaNacimiento;}

    public String toString(){
        return "PersonVO{codigo=" + this.codigo + ", nombre=" + this.firstName + ", apellido=" + this.lastName + ", calle=" + this.street + ", codigoPostal=" + this.postalCode + ", ciudad=" + this.city + ", fechaNacimiento=" + this.birthday + "}";
    }
}
