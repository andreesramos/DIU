package com.example.hotel.modelo.repository.impl;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.repository.ClienteRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteRepositoryImpl implements ClienteRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO cliente;

    public ClienteRepositoryImpl() {
    }

    public ArrayList<ClienteVO> obtenerListaClientes() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM clientes";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String dni = rs.getString("dni");
                String nom = rs.getString("nombre");
                String ape = rs.getString("apellidos");
                String di = rs.getString("direccion");
                String lo = rs.getString("localidad");
                String pro = rs.getString("provincia");
                this.cliente = new ClienteVO(dni, nom, ape, di, lo, pro);
                this.cliente.setDni(dni);
                this.clientes.add(this.cliente);
            }

            this.conexion.desconectarBD(conn);
            return this.clientes;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    public void addCliente(ClienteVO m) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO clientes (dni, nombre, apellidos, direccion, localidad, provincia) VALUES ('" + m.getDni() + "','" + m.getNombre() + "','" + m.getApellidos() + "','" + m.getDireccion() + "','" + m.getLocalidad() + "','" + m.getProvincia() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    public void deleteCliente(String dni) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM clientes WHERE dni = %s", lastDni());
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }

    public void editCliente(ClienteVO clienteVO) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE clientes SET nombre = '%s', apellidos = '%s', direccion = '%s', localidad = '%s', provincia = '%s' WHERE dni = %s", clienteVO.getNombre(), clienteVO.getApellidos(), clienteVO.getDireccion(), clienteVO.getLocalidad(), clienteVO.getProvincia(), lastDni());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionHotel("No se ha podido realizar la edición");
        }
    }

    public String lastDni() throws ExcepcionHotel {
        String lastClienteDni = null;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT dni FROM clientes ORDER BY dni DESC LIMIT 1"); registro.next(); lastClienteDni = registro.getString("dni")) {
            }

            return lastClienteDni;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda del DNI");
        }
    }
}
