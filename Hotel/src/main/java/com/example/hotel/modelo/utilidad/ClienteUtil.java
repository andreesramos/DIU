package com.example.hotel.modelo.utilidad;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.vista.Cliente;

import java.util.ArrayList;

public class ClienteUtil {
    public static ArrayList<ClienteVO> getClienteVO(ArrayList<Cliente> clientes) {
        ArrayList<ClienteVO> clienteVOS = new ArrayList<>();
        for(Cliente cliente : clientes) {
            clienteVOS.add(new ClienteVO(cliente.getDni(), cliente.getNombre(), cliente.getApellidos(), cliente.getDireccion(), cliente.getLocalidad(), cliente.getProvincia()));
        }
        return clienteVOS;
    }

    public static ArrayList<Cliente> getCliente(ArrayList<ClienteVO> clienteVOS) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for(ClienteVO clienteVO : clienteVOS) {
            clientes.add(new Cliente(clienteVO.getDni(), clienteVO.getNombre(), clienteVO.getApellidos(), clienteVO.getDireccion(), clienteVO.getLocalidad(), clienteVO.getProvincia()));
        }
        return clientes;
    }

    public static Cliente getCliente(ClienteVO clienteVO) {
        Cliente cliente = new Cliente();
        cliente.setDni(clienteVO.getDni());
        cliente.setNombre(clienteVO.getNombre());
        cliente.setApellidos(clienteVO.getApellidos());
        cliente.setDireccion(clienteVO.getDireccion());
        cliente.setLocalidad(clienteVO.getLocalidad());
        cliente.setProvincia(clienteVO.getProvincia());
        return cliente;
    }

    public static ClienteVO getClienteVO(Cliente cliente) {
        ClienteVO clienteVO = new ClienteVO();
        clienteVO.setDni(cliente.getDni());
        clienteVO.setNombre(cliente.getNombre());
        clienteVO.setApellidos(cliente.getApellidos());
        clienteVO.setDireccion(cliente.getDireccion());
        clienteVO.setLocalidad(cliente.getLocalidad());
        clienteVO.setProvincia(cliente.getProvincia());
        return clienteVO;
    }
}
