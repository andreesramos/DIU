package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class WebViewController {

    @FXML
    BorderPane borderPane;

    public void mostrarWebView() {
        WebView webView = new WebView();

        TextInputDialog dialog = new TextInputDialog("https://www.example.com");
        dialog.setTitle("Abrir Página Web");
        dialog.setHeaderText("Ingrese la URL de la página web:");
        dialog.setContentText("URL:");

        // Manejar el resultado del cuadro de diálogo
        dialog.showAndWait().ifPresent(new java.util.function.Consumer<String>() {
            @Override
            public void accept(String url) {
                webView.getEngine().load(url);

                borderPane.setCenter(webView);
            }
        });
    }
}
