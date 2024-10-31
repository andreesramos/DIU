package com.example.agenda.modelo.repository.impl;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    private boolean dbConexion=false;

    public ConexionJDBC() {
    }

    public Connection conectarBD() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConexion=true;
            return conn;
        } catch (SQLException var2) {
            if(!dbConexion) {
                dbConexion=false;
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Servidor desactivado");
                alert.setContentText("Conecta el servidor para acceder a la base de datos");
                alert.showAndWait();
                SQLException ex = var2;
                System.out.println("\n--- SQLException capturada ---\n");

                while(ex != null) {
                    System.out.println("Mensaje:   " + ex.getMessage());
                    System.out.println("SQLState:  " + ex.getSQLState());
                    System.out.println("ErrorCode: " + ex.getErrorCode());
                    ex = ex.getNextException();
                    System.out.println("");
                }
            }
            throw new SQLException();
        } catch (ClassNotFoundException var3) {
            throw new SQLException();
        }
    }

    public void desconectarBD(Connection conn) {
        try {
            conn.close();
        } catch (SQLException var3) {
            SQLException ex = var3;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }

    }
}
