package com.example.ejemplosjavafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

class Bill {

    // Definir una variable para almacenar la propiedad
    private DoubleProperty amountDue = new SimpleDoubleProperty();

    // Definir un getter por el valor de la propiedad
    public final double getAmountDue(){return amountDue.get();}

    // Definir un setter por el valor de la propiedad
    public final void setAmountDue(double value){amountDue.set(value);}

    // Definir un getter para la propiedad en sí
    public DoubleProperty amountDueProperty() {return amountDue;}

}
