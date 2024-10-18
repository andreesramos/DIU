package com.example.conversor.modelo;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.conversor.controller.ConversorController;

import java.util.ArrayList;

public class ConversorModelo {

    private MonedaRepository monedaRepository;

    public void setMonedaRepository(MonedaRepositoryImpl monedaRepository) {
        this.monedaRepository = monedaRepository;
    }

    public float obtenerMultiplicador() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas=monedaRepository.ObtenerListaMonedas();
        MonedaVO dolarVO=listaMonedas.get(0);
        return dolarVO.getMultiplicador();
    }

    public float obtenerDolar(float euros) throws ExcepcionMoneda {
        return euros*obtenerMultiplicador();
    }

}
