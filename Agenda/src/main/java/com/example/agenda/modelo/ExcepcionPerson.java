package com.example.agenda.modelo;

public class ExcepcionPerson extends RuntimeException {
    private String mensaje;

    public ExcepcionPerson() {
    }

    public ExcepcionPerson(String ms) {this.mensaje=ms;}

    public String imprimirMensaje() {return this.mensaje;}
}
