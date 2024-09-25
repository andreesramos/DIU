package com.example.ejemplosjavafx;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.binding.NumberBinding;

public class MainBill {

    public static void main(String[] args) {

        /*Bill electricBill = new Bill();

        electricBill.amountDueProperty().addListener(new ChangeListener(){
            @Override public void changed(ObservableValue o,Object oldVal,
                                          Object newVal){
                System.out.println("Electric bill has changed!");
            }
        });

        electricBill.setAmountDue(100.00);*/

        IntegerProperty num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 = new SimpleIntegerProperty(2);
        num1.bindBidirectional(num2);
        num1.set(4);
        System.out.println(num1);
        System.out.println(num2);
    }
}