package com.example.hotel.vista;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Reserva {
    private final IntegerProperty idReserva;
    private final ObjectProperty<LocalDate> fechaEntrada;
    private final ObjectProperty<LocalDate> fechaSalida;
    private final IntegerProperty numHabitaciones;
    private final StringProperty tipoHabitacion;
    private final BooleanProperty fumador;
    private final StringProperty alojamiento;
    private final StringProperty dniCliente;

    public Reserva() {
        this(0, null, null, 0, null, null, null, null);
    }

    public Reserva(Integer idReserva, LocalDate fechaEntrada, LocalDate fechaSalida, Integer numHabitaciones, String tipoHabitaciones, Boolean fumador, String alojamiento, String dniCliente) {
        this.idReserva = new SimpleIntegerProperty(idReserva);
        this.fechaEntrada = new SimpleObjectProperty<LocalDate>(fechaEntrada);
        this.fechaSalida = new SimpleObjectProperty<LocalDate>(fechaSalida);
        this.numHabitaciones = new SimpleIntegerProperty(numHabitaciones);
        this.tipoHabitacion = new SimpleStringProperty(tipoHabitaciones);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.alojamiento = new SimpleStringProperty(alojamiento);
        this.dniCliente = new SimpleStringProperty(dniCliente);

    }

    public Integer getIdReserva() {
        return idReserva.get();
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva.set(idReserva);
    }

    public IntegerProperty idReservaProperty() {
        return idReserva;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada.get();
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada.set(fechaEntrada);
    }

    public ObjectProperty<LocalDate> fechaEntradaProperty() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida.get();
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public ObjectProperty<LocalDate> fechaSalidaProperty() {
        return fechaSalida;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones.get();
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones.set(numHabitaciones);
    }

    public IntegerProperty numHabitacionesProperty() {
        return numHabitaciones;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion.get();
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion.set(tipoHabitacion);
    }

    public StringProperty tipoHabitacionProperty() {
        return tipoHabitacion;
    }

    public Boolean getFumador() {
        return fumador.get();
    }

    public void setFumador(Boolean fumador) {
        this.fumador.set(fumador);
    }

    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public String getAlojamiento() {
        return alojamiento.get();
    }

    public void setAlojamiento(String alojamiento) {
        this.alojamiento.set(alojamiento);
    }

    public StringProperty alojamientoProperty() {
        return alojamiento;
    }

    public String getDniCliente() {
        return dniCliente.get();
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente.set(dniCliente);
    }

    public StringProperty dniClienteProperty() {
        return dniCliente;
    }
}
