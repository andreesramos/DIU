package com.example.examen1danielgomezcurso2324.util;

import Modelo.ArticuloVO;
import com.example.examen1danielgomezcurso2324.model.Articulo;

import java.util.ArrayList;

public class ConversorArticulo {

    public ConversorArticulo(){

    }

    public Articulo convertirArticulo(ArticuloVO articuloVO){
        Articulo articulo = new Articulo();
        articulo.setCodigo(articuloVO.getCodigo());
        articulo.setNombre(articuloVO.getNombre());
        articulo.setDescripcion(articuloVO.getDescripcion());
        articulo.setPrecio(articuloVO.getPrecio());
        articulo.setCantidad(articuloVO.getCantidad());
        return articulo;
    }

    public ArticuloVO convertirArticuloVO(Articulo articulo){
        ArticuloVO articuloVO = new ArticuloVO();
        articuloVO.setCodigo(articulo.getCodigo());
        articuloVO.setNombre(articulo.getNombre());
        articuloVO.setDescripcion(articulo.getDescripcion());
        articuloVO.setPrecio(articulo.getPrecio());
        articuloVO.setCantidad(articulo.getCantidad());
        return articuloVO;
    }

    public ArrayList<Articulo> convertirLista(ArrayList<ArticuloVO> listaVO){
        ArrayList<Articulo> listaArticulos = new ArrayList<>();
        for(ArticuloVO articuloVO: listaVO){
            listaArticulos.add(convertirArticulo(articuloVO));
        }
        return listaArticulos;
    }
}
