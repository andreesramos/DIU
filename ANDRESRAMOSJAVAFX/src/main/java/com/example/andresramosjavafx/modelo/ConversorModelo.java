package com.example.andresramosjavafx.modelo;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import com.example.andresramosjavafx.modelo.utilidad.MonedaUtil;
import com.example.andresramosjavafx.vista.Moneda;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class ConversorModelo {
    static MonedaRepository monedaRepository;

    IntegerProperty numeroMonedas = new SimpleIntegerProperty();

    public void setNumeroMonedas(Integer numeroMonedas) {
        this.numeroMonedas.set(numeroMonedas);
    }

    public IntegerProperty numeroMonedasProperty() {
        return numeroMonedas;
    }

    public void setMonedaRepository(MonedaRepository monedaRepository) {
        this.monedaRepository = monedaRepository;
    }

    public static ArrayList<MonedaVO> obtenerMonedas() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas= monedaRepository.ObtenerListaMonedas();
        return listaMonedas;
    }

    public ArrayList<Moneda> mostrarMonedas() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedasVO= obtenerMonedas();
        ArrayList<Moneda> listaMonedas=new ArrayList<>();
        listaMonedas= MonedaUtil.getMoneda(listaMonedasVO);
        return listaMonedas;
    }

    public void eliminarMoneda(Integer id) throws ExcepcionMoneda {
        monedaRepository.deleteMoneda(id);
        this.numeroMonedas.set(numeroMonedas.get()-1);
    }

    public float obtenerMultiplicador(String nombre) throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas=monedaRepository.ObtenerListaMonedas();
        float multiplicador=0;
        for(MonedaVO monedaVO:listaMonedas){
            if(monedaVO.getNombre().equals(nombre)){
                multiplicador=monedaVO.getMultiplicador();
            }
        }
        return multiplicador;
    }

}
