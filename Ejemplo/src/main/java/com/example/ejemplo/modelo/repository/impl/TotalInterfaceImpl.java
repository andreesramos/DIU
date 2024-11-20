package com.example.ejemplo.modelo.repository.impl;

import com.example.ejemplo.modelo.repository.TotalInterface;

public class TotalInterfaceImpl implements TotalInterface {
    @Override
    public float total(Integer unidades, Float precio) {
        return unidades*precio;
    }
}
