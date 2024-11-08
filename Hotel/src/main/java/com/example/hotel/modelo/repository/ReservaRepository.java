package com.example.hotel.modelo.repository;

import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.vista.Reserva;

import java.util.ArrayList;

public interface ReservaRepository {
    ArrayList<ReservaVO> obtenerListaReservas() throws ExcepcionHotel;

    void addReserva(ReservaVO var1) throws ExcepcionHotel;

    void deleteReserva(Integer var1) throws ExcepcionHotel;

    void editReserva(ReservaVO var1) throws ExcepcionHotel;

    int lastId() throws ExcepcionHotel;
}
