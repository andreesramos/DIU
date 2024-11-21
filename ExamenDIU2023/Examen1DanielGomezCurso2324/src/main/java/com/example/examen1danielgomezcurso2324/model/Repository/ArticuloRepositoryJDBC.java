package com.example.examen1danielgomezcurso2324.model.Repository;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;
import Modelo.Repository.Impl.ConexionJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class ArticuloRepositoryJDBC implements ArticuloRepository {
    ArticuloVO articulo;
    //ConexionJDBC conexion = new ConexionJDBC();
    String sql;
    private ArrayList<ArticuloVO> listaArticulos = new ArrayList();

    public ArticuloRepositoryJDBC() {
    }

    public ArticuloVO consulta(int codigo) throws ExcepcionArticulo {
        try {
           // Connection conn = this.conexion.conectarBD();
            //Statement stmt = conn.createStatement();
            this.sql = "SELECT * FROM articulos where codigo  = " + codigo;

            String descripcion;
            String nombre;
            float precio;
            int cantidad;
            /*
            for(ResultSet rs = stmt.executeQuery(this.sql); rs.next(); this.articulo = new ArticuloVO(descripcion, precio, nombre, cantidad)) {
                descripcion = rs.getString("descripcion");
                nombre = rs.getString("nombre");
                precio = rs.getFloat("precio");
                cantidad = rs.getInt("cantidad");
            }

            stmt.close();
            this.conexion.desconectarBD(conn);
            */
            return this.articulo;
        } catch (Exception var9) {
            System.err.println("SQL ERROR");
            throw new ExcepcionArticulo("No se ha podido realizar la operación");
        }
    }

    public void addArticulo(ArticuloVO articulo) throws ExcepcionArticulo {
        String descripcion = articulo.getDescripcion();
        float precio = articulo.getPrecio();
        String nombre = articulo.nombre;
        int cantidad = articulo.cantidad;

        try {
            this.listaArticulos.add(articulo);
        } catch (Exception var9) {
            System.out.println("ERROR" + var9.getMessage());
            throw new ExcepcionArticulo("No se ha podido realizar la operación");
        }
    }

    public ArrayList<ArticuloVO> obternerListaArticulos() throws ExcepcionArticulo {
        try {
            if (this.listaArticulos.isEmpty()) {
                ArticuloVO articuloVO = new ArticuloVO("descripciónP", 15.3F, "NombreP", 200);
                this.listaArticulos.add(articuloVO);
            }
            return this.listaArticulos;
        } catch (Exception var2) {
            System.err.println("SQL EXCEPTION");
            throw new ExcepcionArticulo("No se ha podido realizar la operación");
        }
    }
}
