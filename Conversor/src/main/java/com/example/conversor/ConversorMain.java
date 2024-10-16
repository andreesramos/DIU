package com.example.conversor;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;

import java.sql.Connection;

public class ConversorMain {
    public static void main(String[] args) {
        ConexionJDBC conexion = new ConexionJDBC();
        Connection con=null;
        try{
            con=conexion.conectarBD();
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            MonedaVO monedaPrueba = new MonedaVO("prueba", 1.2F);
            monedarepositoryImpl.addMoneda(monedaPrueba);
            //float moneda=con.
            conexion.desconectarBD(con);
        }catch (ExcepcionMoneda var5) {
            ExcepcionMoneda e = var5;
            System.out.println(e.imprimirMensaje());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
