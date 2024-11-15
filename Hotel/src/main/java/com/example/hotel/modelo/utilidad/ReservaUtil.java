package com.example.hotel.modelo.utilidad;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;

import java.util.ArrayList;

public class ReservaUtil {
    public static ArrayList<ReservaVO> getReservaVO(ArrayList<Reserva> reservas) {
        ArrayList<ReservaVO> reservaVOS = new ArrayList<>();
        for(Reserva reserva : reservas) {
            reservaVOS.add(new ReservaVO(reserva.getIdReserva(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getNumHabitaciones(), reserva.getTipoHabitacion(), reserva.getFumador(), reserva.getAlojamiento(), reserva.getDniCliente()));
        }
        return reservaVOS;
    }

    public static ArrayList<Reserva> getReserva(ArrayList<ReservaVO> reservaVOS) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        for(ReservaVO reservaVO : reservaVOS) {
            reservas.add(new Reserva(reservaVO.getIdReserva(), reservaVO.getFechaEntrada(), reservaVO.getFechaSalida(), reservaVO.getNumHabitaciones(), reservaVO.getTipoHabitacion(), reservaVO.getFumador(), reservaVO.getAlojamiento(), reservaVO.getDniCliente()));
        }
        return reservas;
    }

    public static Reserva getReserva(ReservaVO reservaVO) {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(reservaVO.getIdReserva());
        reserva.setFechaEntrada(reservaVO.getFechaEntrada());
        reserva.setFechaSalida(reservaVO.getFechaSalida());
        reserva.setNumHabitaciones(reservaVO.getNumHabitaciones());
        reserva.setTipoHabitacion(reservaVO.getTipoHabitacion());
        reserva.setFumador(reservaVO.getFumador());
        reserva.setAlojamiento(reserva.getAlojamiento());
        reserva.setDniCliente(reservaVO.getDniCliente());
        return reserva;
    }

    public static ReservaVO getReservaVO(Reserva reserva) {
        ReservaVO reservaVO = new ReservaVO();
        reservaVO.setIdReserva(reserva.getIdReserva());
        reservaVO.setFechaEntrada(reserva.getFechaEntrada());
        reservaVO.setFechaSalida(reserva.getFechaSalida());
        reservaVO.setNumHabitaciones(reserva.getNumHabitaciones());
        reservaVO.setTipoHabitacion(reserva.getTipoHabitacion());
        reservaVO.setFumador(reserva.getFumador());
        reservaVO.setAlojamiento(reserva.getAlojamiento());
        reservaVO.setDniCliente(reserva.getDniCliente());
        return reservaVO;
    }
}
