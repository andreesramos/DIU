package com.example.hotel.modelo.repository.impl;

import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.modelo.repository.ReservaRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepositoryImpl implements ReservaRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;

    public ReservaRepositoryImpl() {
    }

    public ArrayList<ReservaVO> obtenerListaReservas() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reservas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                Integer id = rs.getInt("idReserva");
                LocalDate fEnt = rs.getDate("fechaEntrada").toLocalDate();
                LocalDate fSal = rs.getDate("fechaSalida").toLocalDate();
                Integer numH = rs.getInt("numHabitaciones");
                String tipoH = rs.getString("tipoHabitacion");
                Boolean fum = rs.getBoolean("fumador");
                String alo = rs.getString("alojamiento");
                String dniC = rs.getString("dniCliente");
                this.reserva = new ReservaVO(id, fEnt, fSal, numH, tipoH, fum, alo, dniC);
                this.reserva.setIdReserva(id);
                this.reservas.add(this.reserva);
            }

            this.conexion.desconectarBD(conn);
            return this.reservas;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    /*public void addReserva(ReservaVO m) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO reservas (fechaEntrada, fechaSalida, numHabitaciones, tipoHabitacion, fumador, alojamiento, dniCliente) VALUES ('" + m.getFechaEntrada() + "','" + m.getFechaSalida() + "','" + m.getNumHabitaciones() + "','" + m.getTipoHabitacion() + "','" + m.getFumador() + "','" + m.getAlojamiento() + "','" + m.getDniCliente() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }*/

    public void addReserva(ReservaVO m) throws ExcepcionHotel {
        String sql = "INSERT INTO reservas (fechaEntrada, fechaSalida, numHabitaciones, tipoHabitacion, fumador, alojamiento, dniCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.conexion.conectarBD();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, java.sql.Date.valueOf(m.getFechaEntrada()));
            pstmt.setDate(2, java.sql.Date.valueOf(m.getFechaSalida()));
            pstmt.setInt(3, m.getNumHabitaciones());
            pstmt.setString(4, m.getTipoHabitacion());
            pstmt.setBoolean(5, m.getFumador());
            pstmt.setString(6, m.getAlojamiento());
            pstmt.setString(7, m.getDniCliente());

            pstmt.executeUpdate();
            pstmt.close();

            /*Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO reservas (fechaEntrada, fechaSalida, numHabitaciones, tipoHabitacion, fumador, alojamiento, dniCliente) VALUES ('" + m.getFechaEntrada() + "','" + m.getFechaSalida() + "','" + m.getNumHabitaciones() + "','" + m.getTipoHabitacion() + "','" + m.getFumador() + "','" + m.getAlojamiento() + "','" + m.getDniCliente() + "');";
            System.out.println(m.toString());
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();*/
            this.conexion.desconectarBD(conn);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcepcionHotel("No se ha podido realizar la operación: " + e.getMessage());
        }
    }


    public void deleteReserva(Integer idReserva) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM reservas WHERE idReserva = %d", lastId());
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }

    public void editReserva(ReservaVO reservaVO) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE reservas SET fechaEntrada = '%s', fechaSalida = '%s', numHabitaciones = '%d', tipoHabitacion = '%s', fumador = '%s', alojamiento = '%s', dniCliente = '%s' WHERE idReserva = %d", reservaVO.getFechaEntrada(), reservaVO.getFechaSalida(), reservaVO.getNumHabitaciones(), reservaVO.getTipoHabitacion(), reservaVO.getFumador(), reservaVO.getAlojamiento(), reservaVO.getDniCliente(), lastId());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionHotel("No se ha podido realizar la edición");
        }
    }

    public int lastId() throws ExcepcionHotel {
        int lastId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT idReserva FROM reservas ORDER BY idReserva DESC LIMIT 1"); registro.next(); lastId = registro.getInt("idReserva")) {
            }

            return lastId;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda del ID");
        }
    }
}
