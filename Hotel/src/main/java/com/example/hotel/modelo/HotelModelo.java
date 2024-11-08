package com.example.hotel.modelo;

import com.example.hotel.modelo.repository.ClienteRepository;
import com.example.hotel.modelo.repository.ReservaRepository;

import java.util.ArrayList;

public class HotelModelo {
    static ClienteRepository clienteRepository;
    static ReservaRepository reservaRepository;

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public static ArrayList<ClienteVO> obtenerClientes() {
        ArrayList<ClienteVO> listaClientes=clienteRepository.obtenerListaClientes();
        return listaClientes;
    }

    public static ArrayList<ReservaVO> obtenerReservas() {
        ArrayList<ReservaVO> listaReservas=reservaRepository.obtenerListaReservas();
        return listaReservas;
    }

    /*public ArrayList<Cliente> mostrarClientes() {
        ArrayList<ClienteVO> listaClientesVO=obtenerClientes();
        ArrayList<Cliente> listaClientes=new ArrayList<>();
        listaClientes=PersonUtil.getPerson(listaClientesVO);
        return listaPersonas;
    }*/
}
