package com.example.andresramosjavafx.modelo.utilidad;

import Modelo.MonedaVO;
import com.example.andresramosjavafx.vista.Moneda;

import java.util.ArrayList;

public class MonedaUtil {
    public static ArrayList<MonedaVO> getMonedaVO(ArrayList<Moneda> monedas) {
        ArrayList<MonedaVO> monedaVOS = new ArrayList<>();
        for(Moneda moneda : monedas) {
            monedaVOS.add(new MonedaVO(moneda.getNombre(), moneda.getMultiplicador(), moneda.getCodigo()));
        }
        return monedaVOS;
    }

    public static ArrayList<Moneda> getMoneda(ArrayList<MonedaVO> monedaVOS) {
        ArrayList<Moneda> monedas = new ArrayList<>();
        for(MonedaVO monedaVO : monedaVOS) {
            monedas.add(new Moneda(monedaVO.getCodigo(), monedaVO.getNombre(), monedaVO.getMultiplicador()));
        }
        return monedas;
    }

    public static Moneda getMoneda(MonedaVO monedaVO) {
        Moneda moneda = new Moneda();
        moneda.setCodigo(monedaVO.getCodigo());
        moneda.setNombre(monedaVO.getNombre());
        moneda.setMultiplicador(monedaVO.getMultiplicador());
        return moneda;
    }

    /*public static MonedaVO getMonedaVO(Moneda moneda) {
        MonedaVO personVO = new MonedaVO();
        personVO.setCodigo(moneda.getCodigo());
        personVO.setNombre(moneda.getNombre());
        personVO.setMultiplicador(moneda.getMultiplicador());
        return personVO;
    }*/
}
