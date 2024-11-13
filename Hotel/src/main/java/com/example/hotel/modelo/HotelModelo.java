package com.example.hotel.modelo;

import com.example.hotel.modelo.repository.ClienteRepository;
import com.example.hotel.modelo.repository.ReservaRepository;
import com.example.hotel.modelo.utilidad.ClienteUtil;
import com.example.hotel.vista.Cliente;

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

    public ArrayList<Cliente> mostrarClientes() {
        ArrayList<ClienteVO> listaClientesVO=obtenerClientes();
        ArrayList<Cliente> listaClientes=new ArrayList<>();
        listaClientes=ClienteUtil.getCliente(listaClientesVO);
        return listaClientes;
    }

    public void insertarCliente(Cliente cliente) throws ExcepcionHotel{
        ClienteVO clienteVO=ClienteUtil.getClienteVO(cliente);
        clienteRepository.addCliente(clienteVO);
    }

    public void eliminarCliente(String dni) throws ExcepcionHotel{
        clienteRepository.deleteCliente(dni);
    }

    public void modificarCliente(Cliente cliente) throws ExcepcionHotel{
        ClienteVO clienteVO=ClienteUtil.getClienteVO(cliente);
        clienteRepository.editCliente(clienteVO);
    }
}
