package com.example.hotel.modelo;

import com.example.hotel.vista.Reserva;

import java.time.LocalDate;

public class ReservaVO {
    int idReserva;
    LocalDate fechaEntrada;
    LocalDate fechaSalida;
    int numHabitaciones;
    String tipoHabitacion;
    Boolean fumador;
    String alojamiento;
    String dniCliente;

    public ReservaVO() {
        this(0, null, null, 0, null, null, null, null);
    }

    public ReservaVO(int idReserva, LocalDate fechaEntrada, LocalDate fechaSalida, int numHabitaciones, String tipoHabitacion, Boolean fumador, String alojamiento, String dniCliente) {
        this.idReserva = idReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numHabitaciones = numHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
        this.fumador = fumador;
        this.alojamiento = alojamiento;
        this.dniCliente = dniCliente;
    }

    public int getIdReserva() {return idReserva;}
    public void setIdReserva(int idReserva) {this.idReserva = idReserva;}

    public LocalDate getFechaEntrada() {return this.fechaEntrada;}
    public void setFechaEntrada(LocalDate fechaEntrada) {this.fechaEntrada = fechaEntrada;}

    public LocalDate getFechaSalida() {return this.fechaSalida;}
    public void setFechaSalida(LocalDate fechaSalida) {this.fechaSalida = fechaSalida;}

    public int getNumHabitaciones() {return this.numHabitaciones;}
    public void setNumHabitaciones(int numHabitaciones) {this.numHabitaciones = numHabitaciones;}

    public String getTipoHabitacion() {return this.tipoHabitacion;}
    public void setTipoHabitacion(String tipoHabitacion) {this.tipoHabitacion = tipoHabitacion;}

    public Boolean getFumador() {return this.fumador;}
    public void setFumador(Boolean fumador) {this.fumador = fumador;}

    public String getAlojamiento() {return this.alojamiento;}
    public void setAlojamiento(String alojamiento) {this.alojamiento = alojamiento;}

    public String getDniCliente() {return this.dniCliente;}
    public void setDniCliente(String dniCliente) {this.dniCliente = dniCliente;}

    public String toString(){
        return "ReservaVO{idReserva=" + this.idReserva + ", fechaEntrada=" + this.fechaEntrada + ", fechaSalida=" + this.fechaSalida + ", numHabitaciones=" + this.numHabitaciones + ", tipoHabitacion=" + this.tipoHabitacion + ", fumador=" + this.fumador + ", alojamiento=" + this.alojamiento + ", dniCliente=" + this.dniCliente + "}";
    }
}
