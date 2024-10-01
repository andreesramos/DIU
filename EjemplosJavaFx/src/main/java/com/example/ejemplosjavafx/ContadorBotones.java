package com.example.ejemplosjavafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class ContadorBotones extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        ContadorPulsaciones cont1=new ContadorPulsaciones();
        ContadorPulsaciones cont2=new ContadorPulsaciones();

        cont1.getNumPulsaciones().bindBidirectional(cont2.getNumPulsaciones());

        Stage stage1=new Stage();
        cont1.start(stage1);
        Stage stage2=new Stage();
        cont2.start(stage2);

    }

    public static void main(String[] args){
        launch(args);
    }

}
