package com.example.conversor;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.conversor.controller.ConversorController;
import com.example.conversor.modelo.ConversorModelo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class ConversorMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        ConexionJDBC conexion = new ConexionJDBC();
        Connection con=null;
        try {
            con = conexion.conectarBD();
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            ConversorModelo conversorModelo = new ConversorModelo();
            conversorModelo.setMonedaRepository(monedarepositoryImpl);

            FXMLLoader fxmlLoader = new FXMLLoader(ConversorMain.class.getResource("conversorView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            escenarioPrincipal.setTitle("Conversor de moneda");
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();


            ConversorController conversorController = fxmlLoader.getController();
            conversorController.setConversorModelo(conversorModelo);

            /*MonedaVO monedaPrueba = new MonedaVO("prueba", 1.2F);
            monedarepositoryImpl.addMoneda(monedaPrueba);*/

            conexion.desconectarBD(con);
        }catch (Exception e){
                e.printStackTrace();
        }
        /*}catch (ExcepcionMoneda var5) {
            ExcepcionMoneda e = var5;
            System.out.println(e.imprimirMensaje());
        }*/


    }
}
